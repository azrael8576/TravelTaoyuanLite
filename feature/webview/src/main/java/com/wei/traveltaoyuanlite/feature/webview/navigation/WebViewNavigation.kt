package com.wei.traveltaoyuanlite.feature.webview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.wei.traveltaoyuanlite.feature.webview.WebViewRoute

const val WEB_VIEW_ROUTE = "web_view_route"
const val URL_ARG = "url"
const val TOP_BAR_TITLE_ARG = "topBarTitle"

fun NavController.navigateToWebView(
    navOptions: NavOptions? = null,
    url: String,
    topBarTitle: String,
) {
    this.currentBackStackEntry?.savedStateHandle?.set(
        key = URL_ARG,
        value = url,
    )
    this.currentBackStackEntry?.savedStateHandle?.set(
        key = TOP_BAR_TITLE_ARG,
        value = topBarTitle,
    )
    this.navigate(WEB_VIEW_ROUTE, navOptions)
}

fun NavGraphBuilder.webViewGraph(
    navController: NavController,
) {
    composable(route = WEB_VIEW_ROUTE) {
        val url =
            navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                URL_ARG,
            ) ?: ""
        val topBarTitle =
            navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                TOP_BAR_TITLE_ARG,
            ) ?: ""

        if (url.isNotBlank()) {
            WebViewRoute(
                navController = navController,
                url = url,
                topBarTitle = topBarTitle,
            )
        }
    }
}
