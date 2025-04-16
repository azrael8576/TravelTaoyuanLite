package com.wei.traveltaoyuanlite.core.data.repository

import com.wei.traveltaoyuanlite.core.data.model.asExternalModel
import com.wei.traveltaoyuanlite.core.model.data.TravelAttraction
import com.wei.traveltaoyuanlite.core.network.Dispatcher
import com.wei.traveltaoyuanlite.core.network.TtlDispatchers
import com.wei.traveltaoyuanlite.core.network.TtlNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of the [TravelRepository].
 *
 *  @param ioDispatcher 用於執行 IO 相關操作的 CoroutineDispatcher。
 * @param network 數據源的網路接口。
 */
class DefaultTravelRepository
@Inject
constructor(
    @Dispatcher(TtlDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val network: TtlNetworkDataSource,
) : TravelRepository {

    override suspend fun getTravelAttractions(
        lang: String,
        page: Int,
    ): Flow<List<TravelAttraction>> =
        withContext(ioDispatcher) {
            flow {
                emit(
                    network.getTravelAttractions(
                        lang = lang,
                        page = page,
                    ).infos.infoList.map { it.asExternalModel() },
                )
            }
        }
}
