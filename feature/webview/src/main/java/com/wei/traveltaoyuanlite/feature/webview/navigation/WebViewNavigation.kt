package com.wei.traveltaoyuanlite.feature.webview.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wei.traveltaoyuanlite.feature.webview.WebViewRoute

const val WEB_VIEW_ROUTE = "web_view_route?url={url}&topBarTitle={topBarTitle}"

fun NavController.navigateToWebView(
    url: String,
    topBarTitle: String,
    navOptions: NavOptions? = null,
) {
    val encodedUrl = Uri.encode(url)
    val encodedTitle = Uri.encode(topBarTitle)
    navigate(
        "web_view_route?url=$encodedUrl&topBarTitle=$encodedTitle",
        navOptions,
    )
}

fun NavGraphBuilder.webViewGraph(
    navController: NavController,
) {
    composable(
        route = WEB_VIEW_ROUTE,
        arguments = listOf(
            navArgument("url") { type = NavType.StringType },
            navArgument("topBarTitle") { type = NavType.StringType },
        ),
    ) { backStackEntry ->
        val url = backStackEntry.arguments
            ?.getString("url")
            .orEmpty()
        val topBarTitle = backStackEntry.arguments
            ?.getString("topBarTitle")
            .orEmpty()

        WebViewRoute(
            navController = navController,
            url = url,
            topBarTitle = topBarTitle,
        )
    }
}
