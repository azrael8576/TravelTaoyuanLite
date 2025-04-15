package com.wei.traveltaoyuanlite.core.designsystem

import androidx.activity.ComponentActivity
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage
import com.google.accompanist.testharness.TestHarness
import com.wei.traveltaoyuanlite.core.designsystem.component.TtlNavigationBar
import com.wei.traveltaoyuanlite.core.designsystem.component.TtlNavigationBarItem
import com.wei.traveltaoyuanlite.core.designsystem.component.TtlNavigationDrawer
import com.wei.traveltaoyuanlite.core.designsystem.component.TtlNavigationDrawerItem
import com.wei.traveltaoyuanlite.core.designsystem.component.TtlNavigationRail
import com.wei.traveltaoyuanlite.core.designsystem.component.TtlNavigationRailItem
import com.wei.traveltaoyuanlite.core.designsystem.icon.TtlIcons
import com.wei.traveltaoyuanlite.core.designsystem.theme.TtlTheme
import com.wei.traveltaoyuanlite.core.util.DefaultRoborazziOptions
import com.wei.traveltaoyuanlite.core.util.captureMultiTheme
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(application = HiltTestApplication::class, sdk = [33], qualifiers = "480dpi")
@LooperMode(LooperMode.Mode.PAUSED)
class NavigationScreenshotTests() {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun navigationBar_multipleThemes() {
        composeTestRule.captureMultiTheme("NavigationBar") {
            Surface {
                TtlNavigationBarExample()
            }
        }
    }

    @Test
    fun navigationBar_hugeFont() {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
            ) {
                TestHarness(fontScale = 2f) {
                    TtlTheme {
                        TtlNavigationBarExample("Looong item")
                    }
                }
            }
        }
        composeTestRule.onRoot()
            .captureRoboImage(
                "src/test/screenshots/NavigationBar" +
                    "/NavigationBar_fontScale2.png",
                roborazziOptions = DefaultRoborazziOptions,
            )
    }

    @Test
    fun navigationRail_multipleThemes() {
        composeTestRule.captureMultiTheme("NavigationRail") {
            Surface {
                TtlNavigationRailExample()
            }
        }
    }

    @Test
    fun navigationDrawer_multipleThemes() {
        composeTestRule.captureMultiTheme("NavigationDrawer") {
            Surface {
                TtlNavigationDrawerExample()
            }
        }
    }

    @Test
    fun navigationDrawer_hugeFont() {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
            ) {
                TestHarness(fontScale = 2f) {
                    TtlTheme {
                        TtlNavigationDrawerExample("Loooooooooooooooong item")
                    }
                }
            }
        }
        composeTestRule.onRoot()
            .captureRoboImage(
                "src/test/screenshots/NavigationDrawer" +
                    "/NavigationDrawer_fontScale2.png",
                roborazziOptions = DefaultRoborazziOptions,
            )
    }

    @Composable
    private fun TtlNavigationBarExample(label: String = "Item") {
        TtlNavigationBar {
            (0..2).forEach { index ->
                TtlNavigationBarItem(
                    selected = index == 0,
                    onClick = { },
                    icon = {
                        Icon(
                            imageVector = TtlIcons.UpcomingBorder,
                            contentDescription = "",
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = TtlIcons.Upcoming,
                            contentDescription = "",
                        )
                    },
                    label = { Text(label) },
                )
            }
        }
    }

    @Composable
    private fun TtlNavigationRailExample() {
        TtlNavigationRail {
            (0..2).forEach { index ->
                TtlNavigationRailItem(
                    selected = index == 0,
                    onClick = { },
                    icon = {
                        Icon(
                            imageVector = TtlIcons.UpcomingBorder,
                            contentDescription = "",
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = TtlIcons.Upcoming,
                            contentDescription = "",
                        )
                    },
                )
            }
        }
    }

    @Composable
    private fun TtlNavigationDrawerExample(label: String = "Item") {
        TtlNavigationDrawer {
            (0..2).forEach { index ->
                TtlNavigationDrawerItem(
                    selected = index == 0,
                    onClick = { },
                    icon = {
                        Icon(
                            imageVector = TtlIcons.UpcomingBorder,
                            contentDescription = "",
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = TtlIcons.Upcoming,
                            contentDescription = "",
                        )
                    },
                    label = { Text(label) },
                )
            }
        }
    }
}
