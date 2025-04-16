@file:OptIn(kotlinx.serialization.InternalSerializationApi::class)

package com.wei.traveltaoyuanlite.core.network.model

import com.wei.traveltaoyuanlite.core.network.model.wrapper.ClassesWrapper
import com.wei.traveltaoyuanlite.core.network.model.wrapper.FilesWrapper
import com.wei.traveltaoyuanlite.core.network.model.wrapper.ImagesWrapper
import com.wei.traveltaoyuanlite.core.network.model.wrapper.LinksWrapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [NetworkEventNews] when fetched from /Event/News.
 */
@Serializable
data class NetworkEventNews(
    // 編號，例如：4661
    @SerialName("Id")
    val id: String,

    // 對應桃園觀光導覽網網址
    @SerialName("TYWebsite")
    val tyWebsite: String,

    // 分類資訊，可能為 null
    @SerialName("Classes")
    val classes: ClassesWrapper? = null,

    // 標題，例如：2023桃園購物節（9-12月）
    @SerialName("Name")
    val name: String,

    // 描述欄位，為 HTML 字串
    @SerialName("Description")
    val description: String,

    // 資訊揭露時間，例如：2023/09/15 00:00:00
    @SerialName("Start")
    val start: String? = null,

    // 資訊屏蔽時間，例如：2023/12/31 23:59:59
    @SerialName("End")
    val end: String? = null,

    // 相關圖片列表，可能為 null
    @SerialName("Images")
    val images: ImagesWrapper? = null,

    // 附件資料，API 目前多為 null
    @SerialName("Files")
    val files: FilesWrapper? = null,

    // 相關連結，例如：活動官網、臉書專頁等
    @SerialName("Links")
    val links: LinksWrapper? = null,

    // 發佈時間，例如：2023/09/15 00:00:00
    @SerialName("Posted")
    val posted: String,

    // 最後修改時間，例如：2023/12/13 14:59:21
    @SerialName("Modified")
    val modified: String,
)
