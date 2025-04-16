package com.wei.traveltaoyuanlite.core.model.data

/**
 * External data layer representation of a TravelTaoyuanLite news resource
 */
data class EventNews(
    // 編號，例如：4661
    val id: String,

    // 對應桃園觀光導覽網網址
    val tyWebsite: String,

    // 分類資訊
    val classes: List<String> = emptyList(),

    // 標題，例如：2023桃園購物節（9-12月）
    val name: String,

    // 描述欄位，為 HTML 字串
    val description: String,

    // 資訊揭露時間，例如：2023/09/15 00:00:00
    val start: String = "",

    // 資訊屏蔽時間，例如：2023/12/31 23:59:59
    val end: String = "",

    // 相關圖片列表
    val images: List<Image> = emptyList(),

    // 附件資料
    val files: List<File> = emptyList(),

    // 相關連結，例如：活動官網、臉書專頁等
    val links: List<Link> = emptyList(),

    // 發佈時間，例如：2023/09/15 00:00:00
    val posted: String,

    // 最後修改時間，例如：2023/12/13 14:59:21
    val modified: String,
)
