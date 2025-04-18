package com.wei.traveltaoyuanlite.core.data.repository

import androidx.paging.PagingData
import com.wei.traveltaoyuanlite.core.model.data.TravelAttraction
import kotlinx.coroutines.flow.Flow

interface TravelRepository {
    suspend fun getPreviewTravelAttractions(lang: String, limit: Int = 5): Flow<List<TravelAttraction>>
    suspend fun getPagingTravelAttractions(lang: String): Flow<PagingData<TravelAttraction>>
}
