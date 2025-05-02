package com.wei.traveltaoyuanlite.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.wei.traveltaoyuanlite.R
import com.wei.traveltaoyuanlite.core.designsystem.icon.TtlIcons
import com.wei.traveltaoyuanlite.feature.attractions.navigation.AttractionsRoute
import com.wei.traveltaoyuanlite.feature.home.navigation.HomeBaseRoute
import com.wei.traveltaoyuanlite.feature.home.navigation.HomeRoute
import kotlin.reflect.KClass

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    HOME(
        selectedIcon = TtlIcons.Home,
        unselectedIcon = TtlIcons.HomeBorder,
        iconTextId = R.string.home,
        titleTextId = R.string.home,
        route = HomeRoute::class,
        baseRoute = HomeBaseRoute::class,
    ),
    ATTRACTION(
        selectedIcon = TtlIcons.TravelExplore,
        unselectedIcon = TtlIcons.TravelExploreBorder,
        iconTextId = R.string.attraction,
        titleTextId = R.string.attraction,
        route = AttractionsRoute::class,
    ),
}
