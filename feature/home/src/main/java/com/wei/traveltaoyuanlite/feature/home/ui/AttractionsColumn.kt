package com.wei.traveltaoyuanlite.feature.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wei.traveltaoyuanlite.core.designsystem.component.ThemePreviews
import com.wei.traveltaoyuanlite.core.designsystem.icon.TtlIcons
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.core.designsystem.theme.TtlTheme
import com.wei.traveltaoyuanlite.feature.home.AttractionUiState
import com.wei.traveltaoyuanlite.feature.home.R
import com.wei.traveltaoyuanlite.feature.home.ui.carousel.AttractionsCarousel

@Composable
fun AttractionsColumn(
    modifier: Modifier = Modifier,
    attractionsList: List<AttractionUiState>,
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
) {
    Column(modifier = modifier) {
        AttractionsColumnTitle(onMoreClick = {})
        Spacer(Modifier.height(SPACING_SMALL.dp))
        if (attractionsList.isNotEmpty()) {
            AttractionsCarousel(
                attractionsList = attractionsList,
                widthSizeClass = widthSizeClass,
            )
        }
    }
}

@Composable
private fun AttractionsColumnTitle(
    modifier: Modifier = Modifier,
    onMoreClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        val title = stringResource(R.string.feature_home_attractions)
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .testTag(title)
                .semantics { contentDescription = title },
        )
        Spacer(Modifier.weight(1f))
        MoreButton(onClick = onMoreClick)
    }
}

@Composable
private fun MoreButton(onClick: () -> Unit) {
    val label = stringResource(R.string.feature_home_more_attractions)
    Row(
        modifier = Modifier.clickable(
            onClick = onClick,
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp),
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .testTag(label)
                .semantics { contentDescription = label },
        )
        Icon(
            imageVector = TtlIcons.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = MaterialTheme.colorScheme.outline,
        )
    }
}

@ThemePreviews
@Composable
fun AttractionsColumnPreview() {
    TtlTheme {
        Surface {
            AttractionsColumn(
                modifier = Modifier.padding(horizontal = SPACING_LARGE.dp),
                attractionsList = fakeAttractionsList,
                widthSizeClass = WindowWidthSizeClass.Compact,
            )
        }
    }
}

val fakeAttractionsList = listOf(
    AttractionUiState(
        name = "title1",
        imageUrl = "",
    ),
    AttractionUiState(
        name = "title2",
        imageUrl = "",
    ),
    AttractionUiState(
        name = "title3",
        imageUrl = "",
    ),
    AttractionUiState(
        name = "title4",
        imageUrl = "",
    ),
    AttractionUiState(
        name = "title5",
        imageUrl = "",
    ),
    AttractionUiState(
        name = "title6",
        imageUrl = "",
    ),
)
