package com.wei.traveltaoyanlite.feature.attractions.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.wei.traveltaoyuanlite.feature.attractions.R

@Composable
fun AttractionsTopBar(
    onFilterClick: () -> Unit,
    onBookmarkClick: () -> Unit,
) {
    Column(modifier = Modifier.padding(top = SPACING_LARGE.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            FilterButton(onClick = onFilterClick)
            BookmarkButton(onClick = onBookmarkClick)
        }
        Spacer(modifier = Modifier.height(SPACING_LARGE.dp))
        AttractionsTopBarTitle()
    }
}

@Composable
private fun FilterButton(onClick: () -> Unit) {
    val filter = stringResource(R.string.feature_attractions_filter)
    IconButton(
        onClick = onClick,
        modifier =
        Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .semantics { contentDescription = filter },
    ) {
        Icon(
            imageVector = TtlIcons.FilterList,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
        )
    }
}

@Composable
private fun BookmarkButton(onClick: () -> Unit) {
    val bookmark = stringResource(R.string.feature_attractions_bookmark)
    IconButton(
        onClick = onClick,
        modifier =
        Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .semantics { contentDescription = bookmark },
    ) {
        Icon(
            imageVector = TtlIcons.Bookmark,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
        )
    }
}

@Composable
private fun AttractionsTopBarTitle() {
    val selectDestinationTitle = stringResource(R.string.feature_attractions_select_destination)
    Text(
        text = selectDestinationTitle,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.SemiBold,
        modifier =
        Modifier
            .testTag(selectDestinationTitle)
            .semantics { contentDescription = selectDestinationTitle },
    )
}

@ThemePreviews
@Composable
fun AttractionsTopBarPreview() {
    TtlTheme {
        Surface {
            AttractionsTopBar(
                onFilterClick = {},
                onBookmarkClick = {},
            )
        }
    }
}
