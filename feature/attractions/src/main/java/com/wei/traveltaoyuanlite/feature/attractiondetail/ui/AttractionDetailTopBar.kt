package com.wei.traveltaoyuanlite.feature.attractiondetail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.wei.traveltaoyuanlite.core.designsystem.icon.TtlIcons
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.feature.attractions.R

@Composable
fun AttractionDetailTopBar(
    modifier: Modifier,
    withTopSpacer: Boolean,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onMapClick: () -> Unit,
) {
    Column(
        modifier = modifier.padding(top = SPACING_LARGE.dp),
    ) {
        if (withTopSpacer) {
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp)) {
            BackButton(onClick = onBackClick)
            Spacer(modifier = Modifier.weight(1f))
            ShareButton(onClick = onShareClick)
            MapButton(onClick = onMapClick)
        }
    }
}

@Composable
private fun BackButton(onClick: () -> Unit) {
    val back = stringResource(R.string.feature_attractions_back)
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.5f))
            .semantics { contentDescription = back },
    ) {
        Icon(
            imageVector = TtlIcons.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
        )
    }
}

@Composable
private fun ShareButton(onClick: () -> Unit) {
    val share = stringResource(R.string.feature_attractions_share)
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.5f))
            .semantics { contentDescription = share },
    ) {
        Icon(
            imageVector = TtlIcons.Share,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
        )
    }
}

@Composable
private fun MapButton(onClick: () -> Unit) {
    val map = stringResource(R.string.feature_attractions_map)
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.5f))
            .semantics { contentDescription = map },
    ) {
        Icon(
            imageVector = TtlIcons.Map,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
        )
    }
}
