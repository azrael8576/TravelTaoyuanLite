package com.wei.traveltaoyuanlite.feature.news.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_EXTRA_SMALL
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.TtlTheme
import com.wei.traveltaoyuanlite.feature.home.R

@Composable
fun NewsTopBar(onBackClick: () -> Unit) {
    Column(modifier = Modifier.padding(top = SPACING_LARGE.dp)) {
        BackButton(onBackClick)
        Spacer(modifier = Modifier.height(SPACING_LARGE.dp))
        NewTopBarTitle()
        Spacer(modifier = Modifier.height(SPACING_EXTRA_SMALL.dp))
        NewTopBarSubTitle()
    }
}

@Composable
private fun BackButton(onBackClick: () -> Unit) {
    val back = stringResource(R.string.feature_home_back)
    IconButton(
        onClick = onBackClick,
        modifier =
        Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
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
private fun NewTopBarTitle() {
    val newsTitle = stringResource(R.string.feature_home_news)
    Text(
        text = newsTitle,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.SemiBold,
        modifier =
        Modifier
            .testTag(newsTitle)
            .semantics { contentDescription = newsTitle },
    )
}

@Composable
private fun NewTopBarSubTitle() {
    val newsSubTitle = stringResource(R.string.feature_home_news_subtitle)
    Text(
        text = newsSubTitle,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.outline,
        modifier =
        Modifier
            .testTag(newsSubTitle)
            .semantics { contentDescription = newsSubTitle },
    )
}

@ThemePreviews
@Composable
fun NewsTopBarPreview() {
    TtlTheme {
        Surface {
            NewsTopBar(
                onBackClick = {},
            )
        }
    }
}
