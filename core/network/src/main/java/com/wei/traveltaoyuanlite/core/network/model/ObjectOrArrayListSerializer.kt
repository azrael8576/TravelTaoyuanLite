package com.wei.traveltaoyuanlite.core.network.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import timber.log.Timber

/**
 * 泛型序列化器：處理 API 回傳中同一欄位可能是「單一物件 / 陣列 / null」的狀況。
 *
 * 通常用於如 `NetworkImage`, `File`, `NetworkLink` 等結構欄位的防禦性解析。
 *
 * - 正常解析 `JsonArray` 或 `JsonObject`
 * - 異常資料以 `Timber.e` 顯示錯誤內容與原始 JSON
 * - null 或無法識別資料型態 → 空列表
 */
class ObjectOrArrayListSerializer<T>(
    private val dataSerializer: KSerializer<T>,
) : KSerializer<List<T>> {

    override val descriptor: SerialDescriptor =
        ListSerializer(dataSerializer).descriptor

    override fun deserialize(decoder: Decoder): List<T> {
        val input = decoder as? JsonDecoder
            ?: error("ObjectOrArrayListSerializer only supports JSON")

        val element = input.decodeJsonElement()

        return when (element) {
            is JsonArray -> element.mapNotNull {
                runCatching {
                    Json.decodeFromJsonElement(dataSerializer, it)
                }.onFailure { e ->
                    Timber.e(e, "解析失敗：元素 %s 無法轉換為 %s", it.toString(), dataSerializer.descriptor.serialName)
                }.getOrNull()
            }

            is JsonObject -> listOfNotNull(
                runCatching {
                    Json.decodeFromJsonElement(dataSerializer, element)
                }.onFailure { e ->
                    Timber.e(e, "解析失敗：物件 %s 無法轉換為 %s", element.toString(), dataSerializer.descriptor.serialName)
                }.getOrNull(),
            )

            is JsonNull -> emptyList()

            else -> {
                Timber.e("未知資料型態 %s，忽略並返回空列表", element)
                emptyList()
            }
        }
    }

    override fun serialize(encoder: Encoder, value: List<T>) {
        encoder.encodeSerializableValue(ListSerializer(dataSerializer), value)
    }
}
