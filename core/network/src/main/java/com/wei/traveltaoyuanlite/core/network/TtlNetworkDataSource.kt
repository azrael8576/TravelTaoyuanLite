package com.wei.traveltaoyuanlite.core.network

import com.wei.traveltaoyuanlite.core.network.model.NetworkEventNews
import com.wei.traveltaoyuanlite.core.network.model.NetworkResponse
import com.wei.traveltaoyuanlite.core.network.model.NetworkTravelAttraction

/**
 * Interface representing network calls to the TravelTaoyuanLite backend
 */
interface TtlNetworkDataSource {
    suspend fun getEventNews(lang: String, page: Int): NetworkResponse<NetworkEventNews>
    suspend fun getTravelAttractions(lang: String, page: Int): NetworkResponse<NetworkTravelAttraction>
}
