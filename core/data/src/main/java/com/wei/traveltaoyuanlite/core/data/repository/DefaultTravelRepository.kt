package com.wei.traveltaoyuanlite.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.wei.traveltaoyuanlite.core.data.model.asExternalModel
import com.wei.traveltaoyuanlite.core.model.data.TravelAttraction
import com.wei.traveltaoyuanlite.core.network.Dispatcher
import com.wei.traveltaoyuanlite.core.network.TtlDispatchers
import com.wei.traveltaoyuanlite.core.network.TtlNetworkDataSource
import com.wei.traveltaoyuanlite.core.network.pagingsource.TravelAttractionsPagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of the [TravelRepository].
 *
 *  @param ioDispatcher 用於執行 IO 相關操作的 CoroutineDispatcher。
 * @param network 數據源的網路接口。
 */
class DefaultTravelRepository
@Inject constructor(
    @Dispatcher(TtlDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val network: TtlNetworkDataSource,
) : TravelRepository {

    override suspend fun getPreviewTravelAttractions(
        lang: String,
        limit: Int,
    ): Flow<List<TravelAttraction>> = withContext(ioDispatcher) {
        flow {
            emit(
                network.getTravelAttractions(
                    lang = lang,
                    page = 1,
                ).infos.infoList.map {
                    it.asExternalModel()
                }.take(limit),
            )
        }
    }

    override suspend fun getPagingTravelAttractions(lang: String): Flow<PagingData<TravelAttraction>> =
        Pager(
            config = PagingConfig(
                pageSize = 15,
                prefetchDistance = 5,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { TravelAttractionsPagingSource(network, lang) },
        ).flow.map { pagingData ->
            pagingData.map { it.asExternalModel() }
        }
}
