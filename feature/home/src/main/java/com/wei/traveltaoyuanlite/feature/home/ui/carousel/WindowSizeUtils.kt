package com.wei.traveltaoyuanlite.feature.home.ui.carousel

import android.graphics.Rect
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL

/**
 * 取得螢幕寬高（Dp）
 */
@Composable
fun rememberWindowDpSize(): DpSize {
    val context = LocalContext.current
    val metrics = WindowMetricsCalculator.getOrCreate()
        .computeCurrentWindowMetrics(context)
    val bounds: Rect = metrics.bounds
    return with(LocalDensity.current) {
        DpSize(bounds.width().toDp(), bounds.height().toDp())
    }
}

/**
 * 根據 WindowWidthSizeClass 與實際螢幕寬度，計算推薦的卡片寬度
 */
fun getCarouselItemWidth(
    windowSizeClass: WindowWidthSizeClass,
    screenWidth: Dp,
): Dp {
    val cardsPerRow = when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> 2f
        WindowWidthSizeClass.Medium -> 3.2f
        WindowWidthSizeClass.Expanded -> 4.2f
        else -> 2.5f
    }
    val horizontalPadding = SPACING_SMALL.dp * 2
    val totalSpacing = SPACING_SMALL.dp * (cardsPerRow - 1)
    return (screenWidth - horizontalPadding - totalSpacing) / cardsPerRow
}
