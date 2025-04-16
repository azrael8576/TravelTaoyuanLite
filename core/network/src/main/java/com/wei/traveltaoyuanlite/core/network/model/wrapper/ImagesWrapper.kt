@file:OptIn(InternalSerializationApi::class)

package com.wei.traveltaoyuanlite.core.network.model.wrapper

import com.wei.traveltaoyuanlite.core.network.model.ObjectOrArrayListSerializer
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 包裝 API 傳回的圖片欄位 `"Image"`。
 *
 * 後端可能傳回以下格式之一：
 * - 單一圖片物件：`"Image": { ... }`
 * - 圖片陣列：`"Image": [ { ... }, { ... } ]`
 * - `null` 或缺欄位
 *
 * 為避免格式不一致導致閃退，使用 [ObjectOrArrayListSerializer]
 * 將所有狀況統一轉換為 `List<Image>`，預設為空列表。
 *
 * @property images 圖片資料列表
 */
@Serializable
data class ImagesWrapper(
    @SerialName("Image")
    @Serializable(with = ObjectOrArrayListSerializer::class)
    val images: List<NetworkImage> = emptyList(),
)

/**
 * API 傳回的單張圖片資料。
 *
 * @property src 圖片 URL
 * @property subject 圖片主題文字
 * @property ext 圖片副檔名，例如 `.jpg`
 */
@Serializable
data class NetworkImage(
    @SerialName("Src")
    val src: String,
    @SerialName("Subject")
    val subject: String,
    @SerialName("Ext")
    val ext: String,
)
