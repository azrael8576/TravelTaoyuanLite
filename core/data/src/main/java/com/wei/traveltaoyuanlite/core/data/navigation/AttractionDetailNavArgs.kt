package com.wei.traveltaoyuanlite.core.data.navigation

import android.os.Parcelable
import com.wei.traveltaoyuanlite.core.model.data.TravelAttraction
import kotlinx.parcelize.Parcelize

@Parcelize
data class AttractionDetailNavArgs(
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
    // 行政區名稱，例如：復興區
    val district: String = "",
    // 景點地址，例如：義盛里下宇內1鄰4-6號
    val address: String = "",
    // 聯絡電話，例如：886-3-3821835
    val phone: String = "",
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
    val images: List<String> = emptyList(),
    // 外部連結，例如：官方網站
    val links: List<String> = emptyList(),
) : Parcelable

fun TravelAttraction.toAttractionUiState(): AttractionDetailNavArgs {
    return AttractionDetailNavArgs(
        id = id,
        tyWebsite = tyWebsite,
        classes = classes,
        name = name,
        description = description,
        district = district,
        address = address,
        phone = phone,
        openTime = openTime,
        ticket = ticket,
        remind = remind,
        parking = parking,
        facilities = facilities,
        images = images.map { it.src },
        links = links.map { it.src },
    )
}
