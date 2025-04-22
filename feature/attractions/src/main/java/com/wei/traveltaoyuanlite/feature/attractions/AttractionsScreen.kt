package com.wei.traveltaoyuanlite.feature.attractions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.core.designsystem.component.FunctionalityNotAvailablePopup
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.feature.attractions.ui.AttractionsGrid
import com.wei.traveltaoyuanlite.feature.attractions.ui.AttractionsTopBar

/**
 *
 * UI 事件決策樹
 * 下圖顯示了一個決策樹，用於查找處理特定事件用例的最佳方法。
 *
 *                                                      ┌───────┐
 *                                                      │ Start │
 *                                                      └───┬───┘
 *                                                          ↓
 *                                       ┌───────────────────────────────────┐
 *                                       │ Where is event originated?        │
 *                                       └──────┬─────────────────────┬──────┘
 *                                              ↓                     ↓
 *                                              UI                  ViewModel
 *                                              │                     │
 *                           ┌─────────────────────────┐      ┌───────────────┐
 *                           │ When the event requires │      │ Update the UI │
 *                           │ ...                     │      │ State         │
 *                           └─┬─────────────────────┬─┘      └───────────────┘
 *                             ↓                     ↓
 *                        Business logic      UI behavior logic
 *                             │                     │
 *     ┌─────────────────────────────────┐   ┌──────────────────────────────────────┐
 *     │ Delegate the business logic to  │   │ Modify the UI element state in the   │
 *     │ the ViewModel                   │   │ UI directly                          │
 *     └─────────────────────────────────┘   └──────────────────────────────────────┘
 *
 *
 */
@Composable
internal fun AttractionsRoute(
    navController: NavController,
    viewModel: AttractionsViewModel = hiltViewModel(),
    navigateToAttractionDetail: (AttractionDetailNavArgs) -> Unit,
) {
    val lazyPagingItems = viewModel.pagingAttractionsFlow.collectAsLazyPagingItems()
    AttractionsScreen(
        lazyPagingItems = lazyPagingItems,
        navigateToAttractionDetail = navigateToAttractionDetail,
    )
}

@Composable
internal fun AttractionsScreen(
    withTopSpacer: Boolean = true,
    withBottomSpacer: Boolean = true,
    lazyPagingItems: LazyPagingItems<AttractionDetailNavArgs>,
    navigateToAttractionDetail: (AttractionDetailNavArgs) -> Unit,
) {
    val showPopup = remember { mutableStateOf(false) }

    if (showPopup.value) {
        FunctionalityNotAvailablePopup(
            onDismiss = {
                showPopup.value = false
            },
        )
    }

    val horizontalBasePadding = Modifier.padding(horizontal = SPACING_LARGE.dp)
    Surface {
        Column(modifier = horizontalBasePadding) {
            if (withTopSpacer) {
                Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
            }
            AttractionsTopBar(
                onFilterClick = { showPopup.value = true },
                onBookmarkClick = { showPopup.value = true },
            )
            AttractionsGrid(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = SPACING_LARGE.dp),
                lazyPagingItems = lazyPagingItems,
                navigateToAttractionDetail = navigateToAttractionDetail,
                onBookmarkClick = { showPopup.value = true },
            )
            PagingStateHandling(lazyPagingItems)
            if (withBottomSpacer) {
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
            }
        }
    }
}

@Composable
fun PagingStateHandling(lazyPagingItems: LazyPagingItems<AttractionDetailNavArgs>) {
    lazyPagingItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> PageLoader()
            loadState.refresh is LoadState.Error -> PageLoaderError { retry() }
            loadState.append is LoadState.Loading -> LoadingNextPageItem()
            loadState.append is LoadState.Error -> ErrorMessage { retry() }
        }
        if (itemCount == 0 &&
            loadState.append is LoadState.NotLoading &&
            loadState.append.endOfPaginationReached
        ) {
            NoDataMessage()
        }
    }
}

@Composable
fun NoDataMessage() {
    val noDataFound = stringResource(R.string.feature_attractions_no_data_found)
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .semantics {
                contentDescription = noDataFound
            },
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "(´･ω･`)",
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.height(SPACING_SMALL.dp))
            Text(
                text = noDataFound,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
fun PageLoaderError(onClickRetry: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(R.string.feature_attractions_retry))
        }
    }
}

@Composable
fun PageLoader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(onClickRetry: () -> Unit) {
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.feature_attractions_an_error_occurred_please_try_again_later),
            color = MaterialTheme.colorScheme.error,
        )
        Spacer(modifier = Modifier.height(SPACING_SMALL.dp))
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(R.string.feature_attractions_retry))
        }
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
        modifier
            .fillMaxWidth()
            .padding(SPACING_LARGE.dp),
    ) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp))
    }
}
