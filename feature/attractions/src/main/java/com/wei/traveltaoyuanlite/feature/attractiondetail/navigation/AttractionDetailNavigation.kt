package com.wei.traveltaoyuanlite.feature.attractiondetail.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.feature.attractiondetail.AttractionDetailRoute
import com.wei.traveltaoyuanlite.feature.attractiondetail.AttractionDetailViewModel
import com.wei.traveltaoyuanlite.feature.attractiondetail.navigation.navtype.AttractionDetailNavArgsType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class AttractionDetailRoute(val args: AttractionDetailNavArgs) // route to AttractionDetail screen

fun NavController.navigateToAttractionDetail(
    args: AttractionDetailNavArgs,
    navOptions: NavOptionsBuilder.() -> Unit = {},
) {
    this.navigate(route = AttractionDetailRoute(args = args)) {
        navOptions()
    }
}

fun NavGraphBuilder.attractionDetailGraph(
    navController: NavController,
    navigateToWebView: (String, String) -> Unit,
) {
    composable<AttractionDetailRoute>(
        typeMap = mapOf(
            typeOf<AttractionDetailNavArgs>() to AttractionDetailNavArgsType,
        ),
    ) { entry ->
        val args = entry.toRoute<AttractionDetailRoute>().args
        AttractionDetailRoute(
            navController = navController,
            viewModel = hiltViewModel<AttractionDetailViewModel, AttractionDetailViewModel.Factory>(
                key = args.id,
            ) { factory ->
                factory.create(args)
            },
            navigateToWebView = navigateToWebView,
        )
    }
}
