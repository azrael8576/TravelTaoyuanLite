package com.wei.traveltaoyuanlite.core.network.fake

import JvmUnitTestFakeAssetManager
import com.wei.traveltaoyuanlite.core.network.Dispatcher
import com.wei.traveltaoyuanlite.core.network.TtlDispatchers
import com.wei.traveltaoyuanlite.core.network.TtlNetworkDataSource
import com.wei.traveltaoyuanlite.core.network.model.NetworkEventNews
import com.wei.traveltaoyuanlite.core.network.model.NetworkResponse
import com.wei.traveltaoyuanlite.core.network.model.NetworkTravelAttraction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.jetbrains.annotations.VisibleForTesting

class FakeTtlNetworkDataSource(
    @Dispatcher(TtlDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : TtlNetworkDataSource {

    private var shouldReturnErrorForEventNews = false
    private var shouldReturnErrorForTravelAttractions = false

    @VisibleForTesting
    fun setReturnErrorForEventNews(shouldReturnError: Boolean) {
        shouldReturnErrorForEventNews = shouldReturnError
    }

    @VisibleForTesting
    fun setReturnErrorForTravelAttractions(shouldReturnError: Boolean) {
        shouldReturnErrorForTravelAttractions = shouldReturnError
    }

    companion object {
        private const val EVENT_NEWS_PAGE_1_ASSET = "event_news_page1.json"
        private const val EVENT_NEWS_PAGE_2_ASSET = "event_news_page2.json"
        private const val EVENT_NEWS_PAGE_END_ASSET = "event_news_page_end.json"
        private const val TRAVEL_ATTRACTIONS_PAGE_1_ASSET = "travel_attractions_page1.json"
        private const val TRAVEL_ATTRACTIONS_PAGE_END_ASSET = "travel_attractions_page_end.json"
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getEventNews(lang: String, page: Int): NetworkResponse<NetworkEventNews>  =
        withContext(ioDispatcher) {
            if (shouldReturnErrorForEventNews) {
                throw Exception("Fake exception for event news")
            }
            when (page) {
                1 -> assets.open(EVENT_NEWS_PAGE_1_ASSET).use(networkJson::decodeFromStream)
                2 -> assets.open(EVENT_NEWS_PAGE_2_ASSET).use(networkJson::decodeFromStream)
                else -> {
                    assets.open(EVENT_NEWS_PAGE_END_ASSET).use(networkJson::decodeFromStream)
                }
            }
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTravelAttractions(
        lang: String,
        page: Int,
    ): NetworkResponse<NetworkTravelAttraction> =
        withContext(ioDispatcher) {
            if (shouldReturnErrorForTravelAttractions) {
                throw Exception("Fake exception for travel attractions")
            }
            when (page) {
                1 -> assets.open(TRAVEL_ATTRACTIONS_PAGE_1_ASSET).use(networkJson::decodeFromStream)
                else -> {
                    assets.open(TRAVEL_ATTRACTIONS_PAGE_END_ASSET).use(networkJson::decodeFromStream)
                }
            }
        }
}
