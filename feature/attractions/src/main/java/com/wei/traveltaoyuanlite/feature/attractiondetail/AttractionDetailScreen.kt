package com.wei.traveltaoyuanlite.feature.attractiondetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.core.designsystem.component.FunctionalityNotAvailablePopup
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.feature.attractiondetail.ui.AttractionDescriptionColumn
import com.wei.traveltaoyuanlite.feature.attractiondetail.ui.AttractionDetailTopBar
import com.wei.traveltaoyuanlite.feature.attractiondetail.ui.AttractionImageWithGradient
import com.wei.traveltaoyuanlite.feature.attractiondetail.ui.AttractionPrimaryColumn

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
internal fun AttractionDetailRoute(
    navController: NavController,
    viewModel: AttractionDetailViewModel = hiltViewModel(),
    args: AttractionDetailNavArgs,
) {
    LaunchedEffect(args) {
        viewModel.dispatch(AttractionDetailViewAction.Init(args))
    }

    val uiStates: AttractionDetailViewState by viewModel.states.collectAsStateWithLifecycle()
    AttractionDetailScreen(
        uiStates = uiStates,
        onBackClick = navController::popBackStack,
    )
}

@Composable
internal fun AttractionDetailScreen(
    withTopSpacer: Boolean = true,
    withBottomSpacer: Boolean = true,
    uiStates: AttractionDetailViewState,
    onBackClick: () -> Unit,
) {
    val showPopup = remember { mutableStateOf(false) }

    if (showPopup.value) {
        FunctionalityNotAvailablePopup(
            onDismiss = {
                showPopup.value = false
            },
        )
    }

    Surface {
        val horizontalBasePadding = Modifier.padding(horizontal = SPACING_LARGE.dp)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            val uiStates = uiStates.attractionDetailUiState
            if (uiStates != null) {
                item {
                    AttractionDetailContent(
                        modifier = horizontalBasePadding,
                        uiStates = uiStates,
                        onBookmarkClick = { showPopup.value = true },
                        onAddressClick = { showPopup.value = true },
                        onPhoneClick = { showPopup.value = true },
                    )
                }
            }
            if (withBottomSpacer) {
                item {
                    Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
                }
            }
        }
        AttractionDetailTopBar(
            modifier = horizontalBasePadding,
            withTopSpacer = withTopSpacer,
            onBackClick = onBackClick,
            onShareClick = { showPopup.value = true },
            onMapClick = { showPopup.value = true },
        )
    }
}

@Composable
private fun AttractionDetailContent(
    modifier: Modifier = Modifier,
    uiStates: AttractionDetailNavArgs,
    onBookmarkClick: () -> Unit,
    onAddressClick: () -> Unit,
    onPhoneClick: () -> Unit,
) {
    Box {
        if (uiStates.images.isNotEmpty()) {
            AttractionImageWithGradient(
                modifier = Modifier.height(520.dp),
                imageUrlList = uiStates.images,
            )
        }
        Column(modifier = modifier.fillMaxSize()) {
            AttractionPrimaryColumn(
                uiStates = uiStates,
                onBookmarkClick = onBookmarkClick,
            )
            AttractionDescriptionColumn(
                uiStates = uiStates,
                onAddressClick = onAddressClick,
                onPhoneClick = onPhoneClick,
            )
        }
    }
}
