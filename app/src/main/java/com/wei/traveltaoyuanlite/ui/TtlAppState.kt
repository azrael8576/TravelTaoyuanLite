package com.wei.traveltaoyuanlite.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.wei.traveltaoyuanlite.core.data.utils.NetworkMonitor
import com.wei.traveltaoyuanlite.core.designsystem.ui.DeviceOrientation
import com.wei.traveltaoyuanlite.core.designsystem.ui.DevicePosture
import com.wei.traveltaoyuanlite.core.designsystem.ui.TtlContentType
import com.wei.traveltaoyuanlite.core.designsystem.ui.TtlNavigationType
import com.wei.traveltaoyuanlite.core.designsystem.ui.currentDeviceOrientation
import com.wei.traveltaoyuanlite.core.designsystem.ui.isBookPosture
import com.wei.traveltaoyuanlite.core.designsystem.ui.isSeparating
import com.wei.traveltaoyuanlite.feature.home.navigation.HOME_ROUTE
import com.wei.traveltaoyuanlite.feature.home.navigation.navigateToHome
import com.wei.traveltaoyuanlite.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberTtlAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    displayFeatures: List<DisplayFeature>,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): TtlAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
        networkMonitor,
        displayFeatures,
    ) {
        TtlAppState(
            navController,
            coroutineScope,
            windowSizeClass,
            networkMonitor,
            displayFeatures,
        )
    }
}

@Stable
class TtlAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    displayFeatures: List<DisplayFeature>,
) {
    val currentDeviceOrientation: DeviceOrientation
        @Composable get() = currentDeviceOrientation()

    /**
     * We are using display's folding features to map the device postures a fold is in.
     * In the state of folding device If it's half fold in BookPosture we want to avoid content
     * at the crease/hinge
     */
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()

    val foldingDevicePosture = when {
        isBookPosture(foldingFeature) ->
            DevicePosture.BookPosture(foldingFeature.bounds)

        isSeparating(foldingFeature) ->
            DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

        else -> DevicePosture.NormalPosture
    }

    /**
     * This will help us select type of navigation and content type depending on window size and
     * fold state of the device.
     */
    val navigationType: TtlNavigationType
        @Composable get() = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                TtlNavigationType.BOTTOM_NAVIGATION
            }

            WindowWidthSizeClass.Medium -> {
                TtlNavigationType.NAVIGATION_RAIL
            }

            WindowWidthSizeClass.Expanded -> {
                if (foldingDevicePosture is DevicePosture.BookPosture) {
                    TtlNavigationType.NAVIGATION_RAIL
                } else {
                    TtlNavigationType.PERMANENT_NAVIGATION_DRAWER
                }
            }

            else -> {
                TtlNavigationType.BOTTOM_NAVIGATION
            }
        }

    val contentType: TtlContentType
        @Composable get() = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                TtlContentType.SINGLE_PANE
            }

            WindowWidthSizeClass.Medium -> {
                if (foldingDevicePosture != DevicePosture.NormalPosture) {
                    TtlContentType.DUAL_PANE
                } else {
                    TtlContentType.SINGLE_PANE
                }
            }

            WindowWidthSizeClass.Expanded -> {
                TtlContentType.DUAL_PANE
            }

            else -> {
                TtlContentType.SINGLE_PANE
            }
        }

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val isFullScreenCurrentDestination: Boolean
        @Composable get() = when (currentDestination?.route) {
            null -> true
            else -> false
        }

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> TopLevelDestination.HOME
            else -> null
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    val showFunctionalityNotAvailablePopup: MutableState<Boolean> = mutableStateOf(false)

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(
                    topLevelNavOptions,
                )

                else -> showFunctionalityNotAvailablePopup.value = true
            }
        }
    }
}
