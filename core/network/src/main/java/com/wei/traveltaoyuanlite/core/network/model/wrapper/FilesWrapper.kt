@file:OptIn(InternalSerializationApi::class)

package com.wei.traveltaoyuanlite.core.network.model.wrapper

import com.wei.traveltaoyuanlite.core.network.model.ObjectOrArrayListSerializer
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 包裝 API 傳回的附件欄位 `"File"`。
 *
 * 後端可能傳回以下格式之一：
 * - 單一附件物件：`"File": { ... }`
 * - 附件陣列：`"File": [ { ... }, { ... } ]`
 * - `null` 或缺欄位
 *
 * 為避免格式不一致導致閃退，使用 [ObjectOrArrayListSerializer]
 * 將所有狀況統一轉換為 `List<File>`，預設為空列表。
 *
 * @property files 附件資料列表
 */
@Serializable
data class FilesWrapper(
    @SerialName("File")
    @Serializable(with = ObjectOrArrayListSerializer::class)
    val files: List<NetworkFile> = emptyList(),
)

/**
 * API 傳回的單附件資料。
 *
 * @property src 附件 URL
 * @property subject 主旨
 * @property ext 副檔名，例如 `.pdf`
 */
@Serializable
data class NetworkFile(
    @SerialName("Src")
    val src: String,
    @SerialName("Subject")
    val subject: String,
    @SerialName("Ext")
    val ext: String,
)
