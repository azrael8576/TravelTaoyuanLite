package com.wei.traveltaoyuanlite.core.model.data

/**
 * External data layer representation of a TravelTaoyuanLite attraction resource
 */
data class TravelAttraction(
    // 編號，例如：583
    val id: String,

    // 對應桃園觀光導覽網網址
    val tyWebsite: String,

    // 分類資訊（如：北橫原鄉、自然生態）
    val classes: List<String> = emptyList(),

    // 標題，例如：小烏來天空步道
    val name: String,

    // 景點描述
    val description: String,

    // 郵遞區號，例如：336
    val zipcode: String = "",

    // 行政區名稱，例如：復興區
    val district: String = "",

    // 景點地址，例如：義盛里下宇內1鄰4-6號
    val address: String = "",

    // 東經座標，例如：121.37519
    val eastLongitude: Double = 0.00,

    // 北緯座標，例如：24.79064
    val northLatitude: Double = 0.00,

    // 聯絡電話，例如：886-3-3821835
    val phone: String = "",

    // 傳真號碼，例如：886-3-3322101
    val fax: String = "",

    // 電子郵件（目前常為空字串）
    val email: String = "",

    // 營業時間，例如：08:00 ~ 12:00／13:00 ~ 17:00（週二公休）
    val openTime: String = "",

    // 門票資訊，例如：全票50元、優待票30元...
    val ticket: String = "",

    // 旅遊叮嚀，例如：須線上申請、休園日提醒
    val remind: String = "",

    // 停車資訊，例如：停車空間約40格
    val parking: String = "",

    // 服務設施列表（如觀景台、廁所）
    val facilities: List<String> = emptyList(),

    // 圖片列表
    val images: List<Image> = emptyList(),

    // 外部連結，例如：官方網站
    val links: List<Link> = emptyList(),

    // 發佈時間，例如：2011/06/28 10:36:54
    val posted: String,

    // 最後修改時間，例如：2024/06/19 16:27:52
    val modified: String,
)
