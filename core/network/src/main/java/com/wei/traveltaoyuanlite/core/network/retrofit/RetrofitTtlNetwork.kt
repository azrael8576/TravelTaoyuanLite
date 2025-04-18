package com.wei.traveltaoyuanlite.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.wei.traveltaoyuanlite.core.network.BuildConfig
import com.wei.traveltaoyuanlite.core.network.TtlNetworkDataSource
import com.wei.traveltaoyuanlite.core.network.model.NetworkEventNews
import com.wei.traveltaoyuanlite.core.network.model.NetworkResponse
import com.wei.traveltaoyuanlite.core.network.model.NetworkTravelAttraction
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration for travel.tycg.gov.tw open-api Network API
 */
interface RetrofitTtlNetworkApi {
    /**
     * ${BACKEND_URL}/{lang}/Event/News?page=1
     */
    @GET("{lang}/Event/News")
    suspend fun getEventNews(
        @Path("lang") lang: String = "zh-tw",
        @Query("category") category: String? = null,
        @Query("begin") begin: String? = null,
        @Query("end") end: String? = null,
        @Query("page") page: Int,
    ): NetworkResponse<NetworkEventNews>

    /**
     * ${BACKEND_URL}/{lang}/Travel/Attraction?page=1
     */
    @GET("{lang}/Travel/Attraction")
    suspend fun getTravelAttractions(
        @Path("lang") lang: String = "zh-tw",
        @Query("regions") regions: String? = null,
        @Query("category") category: String? = null,
        @Query("page") page: Int,
    ): NetworkResponse<NetworkTravelAttraction>
}

private const val TRAVEL_TAOYUAN_LITE_BASE_URL = BuildConfig.BACKEND_URL

/**
 * [Retrofit] backed [TtlNetworkDataSource]
 */
@Singleton
class RetrofitTtlNetwork
@Inject
constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : TtlNetworkDataSource {
    private val networkApi =
        Retrofit.Builder()
            .baseUrl(TRAVEL_TAOYUAN_LITE_BASE_URL)
            .callFactory(okhttpCallFactory)
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitTtlNetworkApi::class.java)

    override suspend fun getEventNews(
        lang: String,
        page: Int,
    ): NetworkResponse<NetworkEventNews> {
        return networkApi.getEventNews(
            lang = lang,
            page = page,
        )
    }

    override suspend fun getTravelAttractions(
        lang: String,
        page: Int,
    ): NetworkResponse<NetworkTravelAttraction> {
        return networkApi.getTravelAttractions(
            lang = lang,
            page = page,
        )
    }
}
