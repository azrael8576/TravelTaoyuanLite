package com.wei.traveltaoyuanlite.feature.home.ui.carousel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.core.designsystem.component.coilImagePainter
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttractionsCarousel(
    modifier: Modifier = Modifier,
    attractionsList: List<AttractionDetailNavArgs>,
    widthSizeClass: WindowWidthSizeClass,
    navigateToAttractionDetail: (AttractionDetailNavArgs) -> Unit,
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
        val attraction = attractionsList[index]
        AttractionCard(
            attraction = attraction,
            modifier = Modifier
                .width(cardWidth)
                .clickable(
                    onClick = { navigateToAttractionDetail(attraction) },
                ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CarouselItemScope.AttractionCard(
    attraction: AttractionDetailNavArgs,
    modifier: Modifier = Modifier,
) {
    if (attraction.images.isEmpty()) return
    val painterState = coilImagePainter(attraction.images[0])
    val expansionFraction = (
        (carouselItemDrawInfo.size - carouselItemDrawInfo.minSize) /
            (carouselItemDrawInfo.maxSize - carouselItemDrawInfo.minSize).coerceAtLeast(1f)
        )
    val showText = expansionFraction > 0.33f

    Box(
        modifier
            .height(205.dp)
            .fillMaxWidth()
            .maskClip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        AttractionImageBackground(painterState.painter)
        if (showText) {
            AttractionInfoOverlay(attraction.name)
        }
    }
}

@Composable
private fun BoxScope.AttractionImageBackground(painter: Painter) {
    Box(Modifier.matchParentSize()) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        1f to Color.Black.copy(alpha = 0.4f),
                    ),
                ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CarouselItemScope.AttractionInfoOverlay(text: String) {
    val (offsetDp, widthDp) = with(LocalDensity.current) {
        carouselItemDrawInfo.maskRect.left.toDp() to carouselItemDrawInfo.maskRect.width.toDp()
    }

    Box(
        modifier = Modifier
            .offset(x = offsetDp)
            .width(widthDp)
            .fillMaxHeight(),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(SPACING_LARGE.dp),
        )
    }
}
