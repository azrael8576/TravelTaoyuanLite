package com.wei.traveltaoyuanlite.feature.news.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.wei.traveltaoyuanlite.feature.news.NewsRoute
import kotlinx.serialization.Serializable

@Serializable
data object NewsRoute // route to News screen

fun NavController.navigateToNews(
    navOptions: NavOptionsBuilder.() -> Unit = {},
) {
    this.navigate(route = NewsRoute) {
        navOptions()
    }
}

fun NavGraphBuilder.newsScreen(
    navController: NavController,
    navigateToWebView: (String, String) -> Unit,
) {
    composable<NewsRoute> {
        NewsRoute(
            navController = navController,
            navigateToWebView = navigateToWebView,
        )
    }
}
