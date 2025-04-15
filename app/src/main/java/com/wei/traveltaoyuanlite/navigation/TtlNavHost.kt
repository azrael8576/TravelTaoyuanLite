package com.wei.traveltaoyuanlite.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.window.layout.DisplayFeature
import com.wei.traveltaoyuanlite.core.designsystem.ui.DeviceOrientation
import com.wei.traveltaoyuanlite.feature.home.navigation.HOME_ROUTE
import com.wei.traveltaoyuanlite.feature.home.navigation.homeGraph
import com.wei.traveltaoyuanlite.ui.TtlAppState

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun TtlNavHost(
    modifier: Modifier = Modifier,
    appState: TtlAppState,
    displayFeatures: List<DisplayFeature>,
    startDestination: String = HOME_ROUTE,
) {
    val navController = appState.navController
    val navigationType = appState.navigationType
    val isPortrait = appState.currentDeviceOrientation == DeviceOrientation.PORTRAIT
    val contentType = appState.contentType

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(
            navController = navController,
        )
    }
}
