package com.wei.traveltaoyuanlite.core.data.repository

import com.wei.traveltaoyuanlite.core.data.model.asExternalModel
import com.wei.traveltaoyuanlite.core.model.data.EventNews
import com.wei.traveltaoyuanlite.core.network.Dispatcher
import com.wei.traveltaoyuanlite.core.network.TtlDispatchers
import com.wei.traveltaoyuanlite.core.network.TtlNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of the [EventRepository].
 *
 *  @param ioDispatcher 用於執行 IO 相關操作的 CoroutineDispatcher。
 * @param network 數據源的網路接口。
 */
class DefaultEventRepository
@Inject
constructor(
    @Dispatcher(TtlDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val network: TtlNetworkDataSource,
) : EventRepository {

    override suspend fun getEventNews(lang: String, page: Int): Flow<List<EventNews>> =
        withContext(ioDispatcher) {
            flow {
                emit(
                    network.getEventNews(
                        lang = lang,
                        page = page,
                    ).infos.infoList.map { it.asExternalModel() },
                )
            }
        }
}
