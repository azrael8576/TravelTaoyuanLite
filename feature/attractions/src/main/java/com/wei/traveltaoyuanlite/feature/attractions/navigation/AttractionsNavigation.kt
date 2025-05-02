package com.wei.traveltaoyuanlite.feature.attractions.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.wei.traveltaoyuanlite.feature.attractiondetail.navigation.navigateToAttractionDetail
import com.wei.traveltaoyuanlite.feature.attractions.AttractionsRoute
import kotlinx.serialization.Serializable

@Serializable
data object AttractionsRoute // route to Attractions screen

fun NavController.navigateToAttractions(navOptions: NavOptions) {
    this.navigate(AttractionsRoute, navOptions)
}

fun NavGraphBuilder.attractionsGraph(
    navController: NavController,
) {
    composable<AttractionsRoute> {
        AttractionsRoute(
            navController = navController,
            navigateToAttractionDetail = { args ->
                navController.navigateToAttractionDetail(args = args)
            },
        )
    }
}
