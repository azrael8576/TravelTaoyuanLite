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
 * Unit tests for [TravelAttractionsPagingSource].
 *
 * 遵循此模型，安排、操作、斷言：
 * {Arrange}{Act}{Assert}
 */
class TravelAttractionsPagingSourceTest {

    private lateinit var fakeTtlNetworkDataSource: FakeTtlNetworkDataSource
    private lateinit var travelAttractionsPagingSource: TravelAttractionsPagingSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        fakeTtlNetworkDataSource = FakeTtlNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestFakeAssetManager,
        )
        travelAttractionsPagingSource = TravelAttractionsPagingSource(fakeTtlNetworkDataSource, "zh-tw")
    }

    @Test
    fun `load should return non-empty page for first page`() = runTest(testDispatcher) {
        // Arrange
        val firstPageKey = 1

        // Act
        val firstPageResult = travelAttractionsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = firstPageKey,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        // Assert
        assertThat(firstPageResult).isInstanceOf(PagingSource.LoadResult.Page::class.java)
        assertThat((firstPageResult as PagingSource.LoadResult.Page).data).isNotEmpty()
    }

    @Test
    fun `load should return empty page at end of pagination`() = runTest(testDispatcher) {
        // Arrange
        val endPageKey = 2 // Assuming this is the end

        // Act
        val endPageResult = travelAttractionsPagingSource.load(
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
        fakeTtlNetworkDataSource.setReturnErrorForTravelAttractions(true)

        // Act
        val result = travelAttractionsPagingSource.load(
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
