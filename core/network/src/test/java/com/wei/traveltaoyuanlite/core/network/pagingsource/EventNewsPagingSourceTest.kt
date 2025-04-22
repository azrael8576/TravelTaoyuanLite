package com.wei.traveltaoyuanlite.core.network.pagingsource

import JvmUnitTestFakeAssetManager
import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.wei.traveltaoyuanlite.core.network.fake.FakeTtlNetworkDataSource
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [EventNewsPagingSource].
 *
 * 遵循此模型，安排、操作、斷言：
 * {Arrange}{Act}{Assert}
 */
class EventNewsPagingSourceTest {

    private lateinit var fakeTtlNetworkDataSource: FakeTtlNetworkDataSource
    private lateinit var eventNewsPagingSource: EventNewsPagingSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        fakeTtlNetworkDataSource = FakeTtlNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestFakeAssetManager,
        )
        eventNewsPagingSource = EventNewsPagingSource(fakeTtlNetworkDataSource, "zh-tw")
    }

    @Test
    fun `load should return non-empty page for first and second page`() = runTest(testDispatcher) {
        // Arrange
        val firstPageKey = 1
        val secondPageKey = 2

        // Act
        val firstPageResult = eventNewsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = firstPageKey,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )
        val secondPageResult = eventNewsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = secondPageKey,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        // Assert
        assertThat(firstPageResult).isInstanceOf(PagingSource.LoadResult.Page::class.java)
        assertThat((firstPageResult as PagingSource.LoadResult.Page).data).isNotEmpty()
        assertThat(secondPageResult).isInstanceOf(PagingSource.LoadResult.Page::class.java)
        assertThat((secondPageResult as PagingSource.LoadResult.Page).data).isNotEmpty()
    }

    @Test
    fun `load should return empty page at end of pagination`() = runTest(testDispatcher) {
        // Arrange
        val endPageKey = 3 // Assuming this is the end

        // Act
        val endPageResult = eventNewsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = endPageKey,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        // Assert
        assertThat(endPageResult).isInstanceOf(PagingSource.LoadResult.Page::class.java)
        assertThat((endPageResult as PagingSource.LoadResult.Page).data).isEmpty()
    }

    @Test
    fun `load should return error when data source throws exception`() = runTest {
        // Arrange
        fakeTtlNetworkDataSource.setReturnErrorForEventNews(true)

        // Act
        val result = eventNewsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        // Assert
        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }
}
