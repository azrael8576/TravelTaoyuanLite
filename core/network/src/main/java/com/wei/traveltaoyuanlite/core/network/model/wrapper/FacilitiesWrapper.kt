@file:OptIn(InternalSerializationApi::class)

package com.wei.traveltaoyuanlite.core.network.model.wrapper

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import timber.log.Timber
import kotlin.collections.mapNotNull

/**
 * 包裝 Open API 中的分類欄位 `"Facility"`。
 *
 * 後端可能傳回以下格式之一：
 * - `"Facility": "廁所"`：單一字串
 * - `"Facility": [
 *             "公車站",
 *             "廁所",
 *             "停車場",
 *             "販賣部",
 *             "單車驛站"
 *           ]`：字串陣列
 * - `"Facility": ""`、空陣列、null、或省略欄位
 *
 * 為防止格式不一致導致序列化失敗或閃退，使用 [FacilityListSerializer]
 * 將所有狀況穩定解析為 `List<String>`，預設為空列表。
 *
 * @property facilities 服務設施列表，例如：`[
 *             "公車站",
 *             "廁所",
 *             "停車場",
 *             "販賣部",
 *             "單車驛站"
 *           ]`
 */
@Serializable
data class FacilitiesWrapper(
    // 分類名稱，例如：公車站、廁所
    @SerialName("Facility")
    @Serializable(with = FacilityListSerializer::class)
    val facilities: List<String> = emptyList(),
)

/**
 * 專為 `"Facility"` 欄位設計的序列化器，處理 PHP 後端常見的不一致格式。
 *
 * 支援解析：
 * - 字串 → 包裝成單元素列表
 * - 陣列 → 過濾非字串，轉為 `List<String>`
 * - `null`、空字串、非字串型別 → 空列表
 *
 * 避免因型別錯誤導致的反序列化例外。
 */
object FacilityListSerializer : KSerializer<List<String>> {
    override val descriptor: SerialDescriptor =
        ListSerializer(String.serializer()).descriptor

    override fun deserialize(decoder: Decoder): List<String> {
        val input = decoder as? JsonDecoder ?: error("Only works with JSON")
        val element = input.decodeJsonElement()

        return when (element) {
            is JsonArray -> element.mapNotNull {
                it.jsonPrimitive.contentOrNull
            }

            is JsonPrimitive -> {
                if (element.isString) {
                    listOf(element.content)
                } else {
                    Timber.e("FacilityListSerializer - 非字串 Primitive，忽略值：%s", element.toString())
                    emptyList()
                }
            }

            is JsonNull -> emptyList()

            else -> {
                Timber.e("FacilityListSerializer - 未知型別 Facility 欄位：%s", element.toString())
                emptyList()
            }
        }
    }

    override fun serialize(encoder: Encoder, value: List<String>) {
        encoder.encodeSerializableValue(ListSerializer(String.serializer()), value)
    }
}

fun FacilitiesWrapper?.isEmpty(): Boolean {
    return this == null || this.facilities.isEmpty()
}
