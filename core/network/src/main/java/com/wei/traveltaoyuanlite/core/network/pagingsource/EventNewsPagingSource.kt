package com.wei.traveltaoyuanlite.core.network.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wei.traveltaoyuanlite.core.network.TtlNetworkDataSource
import com.wei.traveltaoyuanlite.core.network.model.NetworkEventNews

class EventNewsPagingSource(
    private val ttlNetworkDataSource: TtlNetworkDataSource,
    private val lang: String,
) : PagingSource<Int, NetworkEventNews>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkEventNews> {
        try {
            val currentPage = params.key ?: 1
            val response = ttlNetworkDataSource.getEventNews(
                lang = lang,
                page = currentPage,
            )

            val endOfPaginationReached = response.infos.infoList.isEmpty()

            return LoadResult.Page(
                data = response.infos.infoList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1,
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NetworkEventNews>): Int? {
        return state.anchorPosition
    }
}
