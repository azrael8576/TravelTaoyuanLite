package com.wei.traveltaoyuanlite.core.data.repository

import androidx.paging.PagingData
import com.wei.traveltaoyuanlite.core.model.data.EventNews
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getPreviewEventNews(lang: String, limit: Int = 5): Flow<List<EventNews>>
    fun getPagingEventNews(lang: String): Flow<PagingData<EventNews>>
}
