package com.wei.traveltaoyuanlite.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.wei.traveltaoyuanlite.R
import com.wei.traveltaoyuanlite.core.designsystem.icon.TtlIcons

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = TtlIcons.Home,
        unselectedIcon = TtlIcons.HomeBorder,
        iconTextId = R.string.home,
        titleTextId = R.string.home,
    ),
    SETTING(
        selectedIcon = TtlIcons.Settings,
        unselectedIcon = TtlIcons.SettingsBorder,
        iconTextId = R.string.setting,
        titleTextId = R.string.setting,
    ),
}
