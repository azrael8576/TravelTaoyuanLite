package com.wei.traveltaoyuanlite.core.data.repository

import com.wei.traveltaoyuanlite.core.model.data.TravelAttraction
import kotlinx.coroutines.flow.Flow

interface TravelRepository {

    suspend fun getTravelAttractions(lang: String, page: Int): Flow<List<TravelAttraction>>
}
