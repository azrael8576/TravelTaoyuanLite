package com.wei.traveltaoyuanlite.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.wei.traveltaoyuanlite.core.AppLocale
import com.wei.traveltaoyuanlite.core.designsystem.component.FunctionalityNotAvailablePopup
import com.wei.traveltaoyuanlite.core.designsystem.component.ThemePreviews
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.TtlTheme
import com.wei.traveltaoyuanlite.feature.home.ui.AttractionsColumn
import com.wei.traveltaoyuanlite.feature.home.ui.HomeTopBar
import com.wei.traveltaoyuanlite.feature.home.ui.NewsColumn
import com.wei.traveltaoyuanlite.feature.home.ui.SwitchLanguageDialog
import com.wei.traveltaoyuanlite.feature.news.navigation.navigateToNews

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
internal fun HomeRoute(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    widthSizeClass: WindowWidthSizeClass,
    navigateToWebView: (String, String) -> Unit,
    navigateToAttractions: () -> Unit,
) {
    val uiStates: HomeViewState by viewModel.states.collectAsStateWithLifecycle()

    HomeScreen(
        uiStates = uiStates,
        widthSizeClass = widthSizeClass,
        onSwitchLanguage = { appLocale ->
            viewModel.dispatch(
                HomeViewAction.SwitchLanguage(
                    appLocale,
                ),
            )
        },
        navigateToWebView = navigateToWebView,
        navigateToNews = {
            navController.navigateToNews()
        },
        navigateToAttractions = navigateToAttractions,
    )
}

@Composable
internal fun HomeScreen(
    uiStates: HomeViewState,
    withTopSpacer: Boolean = true,
    withBottomSpacer: Boolean = true,
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    onSwitchLanguage: (AppLocale) -> Unit,
    navigateToWebView: (String, String) -> Unit,
    navigateToNews: () -> Unit,
    navigateToAttractions: () -> Unit,
    isPreview: Boolean = false,
) {
    val showPopup = remember { mutableStateOf(false) }
    val showSwitchLanguageDialog = remember { mutableStateOf(false) }

    if (showPopup.value) {
        FunctionalityNotAvailablePopup(
            onDismiss = {
                showPopup.value = false
            },
        )
    }

    if (showSwitchLanguageDialog.value) {
        SwitchLanguageDialog(
            onDismissRequest = {
                showSwitchLanguageDialog.value = false
            },
            currentLocale = uiStates.currentLanguage,
            onConfirmation = { selectedLocale ->
                showSwitchLanguageDialog.value = false
                if (selectedLocale == uiStates.currentLanguage) return@SwitchLanguageDialog
                onSwitchLanguage(selectedLocale)
            },
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (withTopSpacer) {
                item {
                    Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
                }
            }
            val horizontalBasePadding = Modifier.padding(horizontal = SPACING_LARGE.dp)

            item {
                HomeTopBar(
                    modifier = horizontalBasePadding,
                    userName = "Gust",
                    avatarId = R.drawable.feature_home_he_wei,
                    onAddUserClick = {
                        // TODO
                        showPopup.value = true
                    },
                    onUserProfileImageClick = {
                        // TODO
                        showPopup.value = true
                    },
                    onMenuClick = {
                        showSwitchLanguageDialog.value = true
                    },
                )
            }

            if (!uiStates.loadingFinish) {
                item {
                    Box(
                        modifier = Modifier
                            .height(600.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                item {
                    NewsColumn(
                        modifier = horizontalBasePadding,
                        newsUiStateList = uiStates.newsUiStateList,
                        navigateToWebView = navigateToWebView,
                        onViewAllClick = navigateToNews,
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(SPACING_LARGE.dp))
                }

                item {
                    AttractionsColumn(
                        modifier = horizontalBasePadding,
                        attractionsList = uiStates.attractionsUiStateList,
                        widthSizeClass = widthSizeClass,
                        onViewAllClick = navigateToAttractions,
                    )
                }
            }

            if (withBottomSpacer) {
                item {
                    Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun HomeScreenPreview() {
    TtlTheme {
        HomeScreen(
            uiStates = HomeViewState(
                newsLoadingState = NewsLoadingState.Finish(isSuccess = true),
                newsUiStateList = emptyList(),
            ),
            onSwitchLanguage = {},
            navigateToWebView = { _, _ -> },
            navigateToNews = {},
            navigateToAttractions = {},
            isPreview = true,
        )
    }
}
