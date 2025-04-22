package com.wei.traveltaoyuanlite.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.DpSize
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.window.layout.FoldingFeature
import com.google.accompanist.testharness.TestHarness
import com.wei.traveltaoyuanlite.R
import com.wei.traveltaoyuanlite.core.data.utils.NetworkMonitor
import com.wei.traveltaoyuanlite.core.manager.SnackbarManager
import com.wei.traveltaoyuanlite.uitesthiltmanifest.HiltComponentActivity
import com.wei.traveltaoyuanlite.utilities.FoldingDeviceUtil
import kotlin.properties.ReadOnlyProperty

/**
 * Robot for [NavigationUiTest].
 *
 * 遵循此模型，找到測試使用者介面元素、檢查其屬性、和透過測試規則執行動作：
 * composeTestRule{.finder}{.assertion}{.action}
 *
 * Testing cheatsheet：
 * https://developer.android.com/jetpack/compose/testing-cheatsheet
 */
internal fun navigationUiRobot(
    composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<HiltComponentActivity>, HiltComponentActivity>,
    func: NavigationUiRobot.() -> Unit,
) = NavigationUiRobot(composeTestRule).apply(func)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
internal open class NavigationUiRobot(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<HiltComponentActivity>, HiltComponentActivity>,
) {
    private fun AndroidComposeTestRule<*, *>.stringResource(@StringRes resId: Int) =
        ReadOnlyProperty<Any?, String> { _, _ -> activity.getString(resId) }

    // The strings used for matching in these tests
    private val ttlBottomBarTag by composeTestRule.stringResource(R.string.tag_ttl_bottom_bar)
    private val ttlNavRailTag by composeTestRule.stringResource(R.string.tag_ttl_nav_rail)
    private val ttlNavDrawerTag by composeTestRule.stringResource(R.string.tag_ttl_nav_drawer)

    private val ttlBottomBar by lazy {
        composeTestRule.onNodeWithTag(
            ttlBottomBarTag,
            useUnmergedTree = true,
        )
    }

    private val ttlNavRail by lazy {
        composeTestRule.onNodeWithTag(
            ttlNavRailTag,
            useUnmergedTree = true,
        )
    }
    private val ttlNavDrawer by lazy {
        composeTestRule.onNodeWithTag(
            ttlNavDrawerTag,
            useUnmergedTree = true,
        )
    }

    fun setTtlAppContent(
        dpSize: DpSize,
        networkMonitor: NetworkMonitor,
        snackbarManager: SnackbarManager,
        foldingState: FoldingFeature.State? = null,
    ) {
        composeTestRule.setContent {
            TestHarness(dpSize) {
                Box {
                    val displayFeatures = if (foldingState != null) {
                        val foldBounds = FoldingDeviceUtil.getFoldBounds(dpSize)
                        listOf(FoldingDeviceUtil.getFoldingFeature(foldBounds, foldingState))
                    } else {
                        emptyList()
                    }

                    TtlApp(
                        windowSizeClass = WindowSizeClass.calculateFromSize(dpSize),
                        networkMonitor = networkMonitor,
                        displayFeatures = displayFeatures,
                        snackbarManager = snackbarManager,
                    )
                }
            }
        }
    }

    fun setTtlAppContentWithBookPosture(
        dpSize: DpSize,
        networkMonitor: NetworkMonitor,
        snackbarManager: SnackbarManager,
    ) {
        setTtlAppContent(dpSize, networkMonitor, snackbarManager, FoldingFeature.State.HALF_OPENED)
    }

    fun verifyTtlBottomBarDisplayed() {
        ttlBottomBar.assertExists().assertIsDisplayed()
    }

    fun verifyTtlNavRailDisplayed() {
        ttlNavRail.assertExists().assertIsDisplayed()
    }

    fun verifyTtlNavDrawerDisplayed() {
        ttlNavDrawer.assertExists().assertIsDisplayed()
    }

    fun verifyTtlBottomBarDoesNotExist() {
        ttlBottomBar.assertDoesNotExist()
    }

    fun verifyTtlNavRailDoesNotExist() {
        ttlNavRail.assertDoesNotExist()
    }

    fun verifyTtlNavDrawerDoesNotExist() {
        ttlNavDrawer.assertDoesNotExist()
    }
}
