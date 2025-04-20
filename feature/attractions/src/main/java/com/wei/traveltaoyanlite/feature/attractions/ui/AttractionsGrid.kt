package com.wei.traveltaoyanlite.feature.attractions.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.wei.traveltaoyanlite.feature.attractions.AttractionUiState
import com.wei.traveltaoyuanlite.core.designsystem.component.coilImagePainter
import com.wei.traveltaoyuanlite.core.designsystem.icon.TtlIcons
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.feature.attractions.R

@Composable
internal fun AttractionsGrid(
    modifier: Modifier,
    lazyPagingItems: LazyPagingItems<AttractionUiState>,
    onAttractionCardClick: () -> Unit,
    onBookmarkClick: () -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 128.dp),
        horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp),
        verticalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp),
    ) {
        items(lazyPagingItems.itemCount) { index ->
            val attractionUiState = lazyPagingItems[index]
            if (attractionUiState != null) {
                AttractionCard(
                    modifier = Modifier
                        .height(232.dp)
                        .clickable(
                            onClick = onAttractionCardClick,
                        ),
                    attractionUiState = attractionUiState,
                    onBookmarkClick = onBookmarkClick,
                )
            }
        }
    }
}

@Composable
internal fun AttractionCard(
    modifier: Modifier = Modifier,
    attractionUiState: AttractionUiState,
    onBookmarkClick: () -> Unit,
) {
    Card(
        modifier = modifier.semantics {
            contentDescription = attractionUiState.name
        },
        shape = RoundedCornerShape(40.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AttractionImageWithGradient(attractionUiState.imageUrl)

            Column(modifier = Modifier.fillMaxSize()) {
                AttractionBookmarkButton(onClick = onBookmarkClick)
                Spacer(modifier = Modifier.weight(1f))
                AttractionNameLabel(attractionUiState.name)
            }
        }
    }
}

@Composable
private fun AttractionImageWithGradient(imageUrl: String) {
    val painter = coilImagePainter(imageUrl).painter

    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Box(
            Modifier
                .matchParentSize()
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.6f),
                        ),
                    ),
                ),
        )
    }
}

@Composable
private fun AttractionBookmarkButton(
    onClick: () -> Unit,
) {
    val bookmark = stringResource(R.string.feature_attractions_bookmark)

    Row(modifier = Modifier.padding(SPACING_SMALL.dp)) {
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.6f))
                .semantics { contentDescription = bookmark },
        ) {
            Icon(
                imageVector = TtlIcons.Bookmark,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@Composable
private fun AttractionNameLabel(name: String) {
    Text(
        text = name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(SPACING_LARGE.dp),
        style = MaterialTheme.typography.titleMedium,
        color = Color.White,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}
