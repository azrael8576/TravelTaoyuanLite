package com.wei.traveltaoyuanlite.feature.home.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.feature.home.HomeRoute

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(HOME_ROUTE, navOptions)
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    widthSizeClass: WindowWidthSizeClass,
    navigateToWebView: (String, String) -> Unit,
    navigateToAttractions: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
    navigateToAttractionDetail: (AttractionDetailNavArgs) -> Unit,
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            navController = navController,
            widthSizeClass = widthSizeClass,
            navigateToWebView = navigateToWebView,
            navigateToAttractions = navigateToAttractions,
            navigateToAttractionDetail = navigateToAttractionDetail,
        )
    }
    nestedGraphs()
}
