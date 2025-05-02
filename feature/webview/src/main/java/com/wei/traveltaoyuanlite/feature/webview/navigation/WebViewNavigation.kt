package com.wei.traveltaoyuanlite.feature.webview.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.wei.traveltaoyuanlite.feature.webview.WebViewRoute
import kotlinx.serialization.Serializable

@Serializable
data class WebViewRoute(val url: String, val topBarTitle: String) // route to WebView screen

fun NavController.navigateToWebView(
    url: String,
    topBarTitle: String,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(route = WebViewRoute(url, topBarTitle)) {
        navOptions()
    }
}

fun NavGraphBuilder.webViewGraph(
    navController: NavController,
) {
    composable<WebViewRoute> { entry ->
        val url = entry.toRoute<WebViewRoute>().url
        val topBarTitle = entry.toRoute<WebViewRoute>().topBarTitle

        WebViewRoute(
            navController = navController,
            url = url,
            topBarTitle = topBarTitle,
        )
    }
}
