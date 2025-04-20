package com.wei.traveltaoyuanlite.feature.news.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.wei.traveltaoyuanlite.feature.news.NewsRoute

const val NEWS_ROUTE = "news_route"

fun NavController.navigateToNews(navOptions: NavOptions? = null) {
    this.navigate(NEWS_ROUTE, navOptions)
}

fun NavGraphBuilder.newsScreen(
    navController: NavController,
    navigateToWebView: (String, String) -> Unit,
) {
    composable(route = NEWS_ROUTE) {
        NewsRoute(
            navController = navController,
            navigateToWebView = navigateToWebView,
        )
    }
}
