@file:OptIn(kotlinx.serialization.InternalSerializationApi::class)

package com.wei.traveltaoyuanlite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    @SerialName("Infos")
    val infos: NetworkInfos<T>,
)

@Serializable
data class NetworkInfos<T>(
    @SerialName("Declaration")
    val declaration: NetworkDeclaration,

    @SerialName("Info")
    val infoList: List<T>,
)

@Serializable
data class NetworkDeclaration(
    // 政府機關代碼，例如：380300000H
    @SerialName("Orgname")
    val orgName: String,

    // 網站名稱，例如：桃園觀光導覽網
    @SerialName("SiteName")
    val siteName: String,

    // 資料總筆數，例如：60（API 實際為字串）
    @SerialName("Total")
    val total: String,

    // 官方網站，例如：https://travel.tycg.gov.tw
    @SerialName("Official-WebSite")
    val officialWebsite: String,

    // 更新時間，例如：2025/04/16 20:58:55
    @SerialName("Updated")
    val updated: String,
)
