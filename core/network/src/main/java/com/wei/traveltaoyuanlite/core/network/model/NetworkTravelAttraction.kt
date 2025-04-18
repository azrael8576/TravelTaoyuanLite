@file:OptIn(kotlinx.serialization.InternalSerializationApi::class)

package com.wei.traveltaoyuanlite.core.network.model

import com.wei.traveltaoyuanlite.core.network.model.wrapper.ClassesWrapper
import com.wei.traveltaoyuanlite.core.network.model.wrapper.FacilitiesWrapper
import com.wei.traveltaoyuanlite.core.network.model.wrapper.ImagesWrapper
import com.wei.traveltaoyuanlite.core.network.model.wrapper.LinksWrapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [NetworkTravelAttraction] when fetched from /Travel/Attraction.
 */
@Serializable
data class NetworkTravelAttraction(
    // 編號，例如：583
    @SerialName("Id")
    val id: String,

    // 對應桃園觀光導覽網網址
    @SerialName("TYWebsite")
    val tyWebsite: String,

    // 分類資訊（如：北橫原鄉、自然生態），可能為 null
    @SerialName("Classes")
    val classes: ClassesWrapper? = null,

    // 標題，例如：小烏來天空步道
    @SerialName("Name")
    val name: String,

    // 景點描述
    @SerialName("Description")
    val description: String,

    // 郵遞區號，例如：336
    @SerialName("Zipcode")
    val zipcode: String? = null,

    // 行政區名稱，例如：復興區
    @SerialName("District")
    val district: String? = null,

    // 景點地址，例如：義盛里下宇內1鄰4-6號
    @SerialName("Address")
    val address: String? = null,

    // 東經座標，例如：121.37519
    @SerialName("East-Longitude")
    val eastLongitude: String? = null,

    // 北緯座標，例如：24.79064
    @SerialName("North-Latitude")
    val northLatitude: String? = null,

    // 聯絡電話，例如：886-3-3821835
    @SerialName("Phone")
    val phone: String? = null,

    // 傳真號碼，例如：886-3-3322101
    @SerialName("Fax")
    val fax: String? = null,

    // 電子郵件（目前常為空字串）
    @SerialName("Email")
    val email: String? = null,

    // 營業時間，例如：08:00 ~ 12:00／13:00 ~ 17:00（週二公休）
    @SerialName("Open-Time")
    val openTime: String? = null,

    // 門票資訊，例如：全票50元、優待票30元...
    @SerialName("Ticket")
    val ticket: String? = null,

    // 旅遊叮嚀，例如：須線上申請、休園日提醒
    @SerialName("Remind")
    val remind: String? = null,

    // 停車資訊，例如：停車空間約40格
    @SerialName("Parking")
    val parking: String? = null,

    // 服務設施列表（如觀景台、廁所）
    @SerialName("Facilities")
    val facilities: FacilitiesWrapper? = null,

    // 圖片列表
    @SerialName("Images")
    val images: ImagesWrapper? = null,

    // 外部連結，例如：官方網站
    @SerialName("Links")
    val links: LinksWrapper? = null,

    // 發佈時間，例如：2011/06/28 10:36:54
    @SerialName("Posted")
    val posted: String,

    // 最後修改時間，例如：2024/06/19 16:27:52
    @SerialName("Modified")
    val modified: String,
)
