package com.wei.traveltaoyuanlite.feature.home.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.feature.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute // route to Home screen

@Serializable
data object HomeBaseRoute // route to base navigation graph

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    widthSizeClass: WindowWidthSizeClass,
    navigateToWebView: (String, String) -> Unit,
    navigateToAttractions: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
    navigateToAttractionDetail: (AttractionDetailNavArgs) -> Unit,
    onLanguageSwitched: () -> Unit,
) {
    navigation<HomeBaseRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeRoute(
                navController = navController,
                widthSizeClass = widthSizeClass,
                navigateToWebView = navigateToWebView,
                navigateToAttractions = navigateToAttractions,
                navigateToAttractionDetail = navigateToAttractionDetail,
                onLanguageSwitched = onLanguageSwitched,
            )
        }
        nestedGraphs()
    }
}
