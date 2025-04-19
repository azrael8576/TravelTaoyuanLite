package com.wei.traveltaoyuanlite.feature.home.ui.carousel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselItemScope
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.wei.traveltaoyuanlite.core.designsystem.component.coilImagePainter
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.feature.home.AttractionUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttractionsCarousel(
    modifier: Modifier = Modifier,
    attractionsList: List<AttractionUiState>,
    widthSizeClass: WindowWidthSizeClass,
) {
    if (attractionsList.isEmpty()) return

    val windowDp = rememberWindowDpSize()
    val cardWidth = getCarouselItemWidth(widthSizeClass, windowDp.width)

    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { attractionsList.size },
        preferredItemWidth = cardWidth,
        itemSpacing = SPACING_SMALL.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(221.dp),
    ) { index ->
        AttractionCard(
            item = attractionsList[index],
            modifier = Modifier.width(cardWidth),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CarouselItemScope.AttractionCard(
    item: AttractionUiState,
    modifier: Modifier = Modifier,
) {
    val painterState = coilImagePainter(item.imageUrl)
    // 根據選取程度決定是否顯示文字
    val fraction = (
        (carouselItemDrawInfo.size - carouselItemDrawInfo.minSize) /
            (carouselItemDrawInfo.maxSize - carouselItemDrawInfo.minSize).coerceAtLeast(1f)
        )
    val showText = fraction > 0.33f

    Box(
        modifier
            .height(205.dp)
            .fillMaxWidth()
            .maskClip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Image(
            painter = painterState.painter,
            contentDescription = item.name,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
        )
        // 底部漸層
        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        1f to Color.Black.copy(alpha = 0.4f),
                    ),
                ),
        )
        if (showText) {
            val (offsetDp, widthDp) = with(LocalDensity.current) {
                carouselItemDrawInfo.maskRect.left.toDp() to carouselItemDrawInfo.maskRect.width.toDp()
            }
            Box(
                Modifier
                    .offset(x = offsetDp)
                    .width(widthDp)
                    .fillMaxHeight(),
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(SPACING_LARGE.dp),
                )
            }
        }
    }
}
