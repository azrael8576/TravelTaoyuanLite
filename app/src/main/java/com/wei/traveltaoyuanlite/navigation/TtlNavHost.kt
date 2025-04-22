package com.wei.traveltaoyuanlite.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.window.layout.DisplayFeature
import com.wei.traveltaoyuanlite.core.designsystem.ui.DeviceOrientation
import com.wei.traveltaoyuanlite.feature.attractiondetail.navigation.attractionDetailGraph
import com.wei.traveltaoyuanlite.feature.attractiondetail.navigation.navigateToAttractionDetail
import com.wei.traveltaoyuanlite.feature.attractions.navigation.attractionsGraph
import com.wei.traveltaoyuanlite.feature.home.navigation.HOME_ROUTE
import com.wei.traveltaoyuanlite.feature.home.navigation.homeGraph
import com.wei.traveltaoyuanlite.feature.news.navigation.newsScreen
import com.wei.traveltaoyuanlite.feature.webview.navigation.navigateToWebView
import com.wei.traveltaoyuanlite.feature.webview.navigation.webViewGraph
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
    val windowSizeClass = appState.windowSizeClass
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        homeGraph(
            navController = navController,
            widthSizeClass = windowSizeClass.widthSizeClass,
            navigateToWebView = { url, topBarTitle ->
                navController.navigateToWebView(
                    url = url,
                    topBarTitle = topBarTitle,
                )
            },
            navigateToAttractions = {
                appState.navigateToTopLevelDestination(TopLevelDestination.ATTRACTION)
            },
            navigateToAttractionDetail = { args ->
                navController.navigateToAttractionDetail(args = args)
            },
            nestedGraphs = {
                newsScreen(
                    navController = navController,
                    navigateToWebView = { url, topBarTitle ->
                        navController.navigateToWebView(
                            url = url,
                            topBarTitle = topBarTitle,
                        )
                    },
                )
            },
            onLanguageSwitched = {
                appState.forceRestartApp(context)
            },
        )
        webViewGraph(
            navController = navController,
        )
        attractionsGraph(
            navController = navController,
        )
        attractionDetailGraph(
            navController = navController,
            navigateToWebView = { url, topBarTitle ->
                navController.navigateToWebView(
                    url = url,
                    topBarTitle = topBarTitle,
                )
            },
        )
    }
}
