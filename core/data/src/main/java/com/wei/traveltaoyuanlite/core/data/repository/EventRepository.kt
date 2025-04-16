package com.wei.traveltaoyuanlite.core.data.repository

import com.wei.traveltaoyuanlite.core.model.data.EventNews
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    suspend fun getEventNews(lang: String, page: Int): Flow<List<EventNews>>
}
