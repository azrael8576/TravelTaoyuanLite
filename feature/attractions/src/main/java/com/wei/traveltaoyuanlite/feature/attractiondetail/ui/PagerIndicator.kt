package com.wei.traveltaoyuanlite.feature.attractiondetail.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val dotWidth: Dp = 8.dp
private val dotHeight: Dp = 8.dp
private val activeDotWidth: Dp = 24.dp
private val dotSpacing: Dp = 12.dp
private val radius = CornerRadius(8f)

private val PagerState.pageOffset: Float
    get() = currentPage + currentPageOffsetFraction

private fun DrawScope.drawIndicator(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radius: CornerRadius,
) {
    val rect = RoundRect(x, y - height / 2, x + width, y + height / 2, radius)
    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = Color.White)
}

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    pagerState: PagerState,
) {
    Canvas(modifier = modifier) {
        val spacingPx = dotSpacing.toPx()
        val dotWidthPx = dotWidth.toPx()
        val activeDotWidthPx = activeDotWidth.toPx()
        val dotHeightPx = dotHeight.toPx()

        val y = size.height / 2
        var x = (size.width - ((dotWidthPx * pageCount) + spacingPx * (pageCount - 1))) / 2

        repeat(pageCount) { i ->
            val posOffset = pagerState.pageOffset
            val dotOffset = posOffset % 1
            val current = posOffset.toInt()
            val factor = dotOffset * (activeDotWidthPx - dotWidthPx)

            val calculatedWidth = when {
                i == current -> activeDotWidthPx - factor
                i - 1 == current || (i == 0 && posOffset > pageCount - 1) -> dotWidthPx + factor
                else -> dotWidthPx
            }

            drawIndicator(x, y, calculatedWidth, dotHeightPx, radius)
            x += calculatedWidth + spacingPx
        }
    }
}
