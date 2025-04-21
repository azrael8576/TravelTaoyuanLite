package com.wei.traveltaoyanlite.feature.attractiondetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.wei.traveltaoyanlite.feature.attractiondetail.AttractionDetailRoute
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs

const val ATTRACTION_DETAIL_ROUTE = "attraction_detail_route"
const val ARG_ATTRACTION_DETAIL = "attraction_detail"

fun NavController.navigateToAttractionDetail(
    navOptions: NavOptions? = null,
    args: AttractionDetailNavArgs,
) {
    currentBackStackEntry?.savedStateHandle?.set(ARG_ATTRACTION_DETAIL, args)
    this.navigate(ATTRACTION_DETAIL_ROUTE, navOptions)
}

fun NavGraphBuilder.attractionDetailGraph(
    navController: NavController,
) {
    composable(route = ATTRACTION_DETAIL_ROUTE) {
        // **從前一筆 entry 拿到我們提前存好的 args**
        val args = navController
            .previousBackStackEntry
            ?.savedStateHandle
            ?.get<AttractionDetailNavArgs>(ARG_ATTRACTION_DETAIL)
            ?: return@composable

        AttractionDetailRoute(
            navController = navController,
            args = args,
        )
    }
}
