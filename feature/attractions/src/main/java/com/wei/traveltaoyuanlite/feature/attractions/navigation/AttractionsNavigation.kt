package com.wei.traveltaoyuanlite.feature.attractions.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.wei.traveltaoyuanlite.feature.attractiondetail.navigation.navigateToAttractionDetail
import com.wei.traveltaoyuanlite.feature.attractions.AttractionsRoute

const val ATTRACTIONS_ROUTE = "attractions_route"

fun NavController.navigateToAttractions(navOptions: NavOptions? = null) {
    this.navigate(ATTRACTIONS_ROUTE, navOptions)
}

fun NavGraphBuilder.attractionsGraph(
    navController: NavController,
) {
    composable(route = ATTRACTIONS_ROUTE) {
        AttractionsRoute(
            navController = navController,
            navigateToAttractionDetail = { args ->
                navController.navigateToAttractionDetail(args = args)
            },
        )
    }
}
