package com.wei.traveltaoyuanlite.ui.robot

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.wei.traveltaoyuanlite.MainActivity
import kotlin.properties.ReadOnlyProperty
import com.wei.traveltaoyuanlite.feature.home.R as FeatureHomeR

/**
 * Screen Robot for End To End Test.
 *
 * 遵循此模型，找到測試使用者介面元素、檢查其屬性、和透過測試規則執行動作：
 * composeTestRule{.finder}{.assertion}{.action}
 *
 * Testing cheatsheet：
 * https://developer.android.com/jetpack/compose/testing-cheatsheet
 */
internal fun homeEndToEndRobot(
    composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
    func: HomeEndToEndRobot.() -> Unit,
) = HomeEndToEndRobot(composeTestRule).apply(func)

internal open class HomeEndToEndRobot(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>,
) {
    private fun AndroidComposeTestRule<*, *>.stringResource(
        @StringRes resId: Int,
    ) = ReadOnlyProperty<Any?, String> { _, _ -> activity.getString(resId) }

    // The strings used for matching in these tests
    private val helloUserNameTag by composeTestRule.stringResource(FeatureHomeR.string.feature_home_tag_hello_user_name_text)

    private val helloUserName by lazy {
        composeTestRule.onNode(hasTestTag(helloUserNameTag))
    }

    fun verifyHelloUserNameDisplayed() {
        helloUserName.assertExists().assertIsDisplayed()
    }
}
