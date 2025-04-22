package com.wei.traveltaoyuanlite.feature.attractiondetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.wei.traveltaoyuanlite.core.designsystem.component.coilImagePainter

val bottomOvalShape = GenericShape { size, _ ->
    // 左上角
    moveTo(0f, 0f)
    // 左下（進入橢圓）
    lineTo(0f, size.height * 0.8f)
    quadraticTo(
        // 提高曲線的最高點 → 拉長橢圓
        size.width / 2f,
        size.height * 1f,
        // 終點不變
        size.width,
        size.height * 0.8f,
    )
    // 右上
    lineTo(size.width, 0f)
    close()
}

@Composable
fun AttractionImageWithGradient(
    modifier: Modifier = Modifier,
    imageUrlList: List<String>,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(bottomOvalShape),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            val pagerState = rememberPagerState(pageCount = {
                imageUrlList.size
            })
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
            ) { page ->
                val painter = coilImagePainter(imageData = imageUrlList[page]).painter
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                )
            }
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f),
                            ),
                        ),
                    ),
            )

            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 88.dp),
                contentAlignment = Alignment.BottomCenter,
            ) {
                PagerIndicator(
                    pageCount = imageUrlList.size,
                    pagerState = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
