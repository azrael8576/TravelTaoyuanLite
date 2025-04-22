package com.wei.traveltaoyuanlite.feature.attractiondetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.core.designsystem.icon.TtlIcons
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.feature.attractions.R

@Composable
fun AttractionPrimaryColumn(
    uiStates: AttractionDetailNavArgs,
    onBookmarkClick: () -> Unit,
) {
    Column(
        modifier = Modifier.height(460.dp),
        verticalArrangement = Arrangement.spacedBy(
            SPACING_SMALL.dp,
        ),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = uiStates.name,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
        )
        OpenTimeRow(uiStates)
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                BookmarkButton(onClick = onBookmarkClick)
            }
        }
    }
}

@Composable
private fun OpenTimeRow(uiStates: AttractionDetailNavArgs) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = TtlIcons.Schedule,
            contentDescription = null,
            tint = Color.White,
        )
        Text(
            text = uiStates.openTime,
            color = Color.White,
        )
    }
}

@Composable
private fun BookmarkButton(onClick: () -> Unit) {
    val bookmark = stringResource(R.string.feature_attractions_bookmark)
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(Color.Black)
            .semantics { contentDescription = bookmark },
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = TtlIcons.Bookmark,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
        )
    }
}
