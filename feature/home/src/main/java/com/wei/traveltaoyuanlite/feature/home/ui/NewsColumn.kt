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
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_MEDIUM
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.core.designsystem.theme.TtlTheme
import com.wei.traveltaoyuanlite.feature.home.NewsUiState
import com.wei.traveltaoyuanlite.feature.home.R

@Composable
fun NewsColumn(
    modifier: Modifier = Modifier,
    newsUiStateList: List<NewsUiState>,
    navigateToWebView: (String, String) -> Unit,
    onViewAllClick: () -> Unit,
) {
    Column(modifier = modifier.padding(top = SPACING_LARGE.dp)) {
        NewsColumnTitle(onViewAllClick = onViewAllClick)
        Spacer(modifier = Modifier.height(SPACING_SMALL.dp))
        newsUiStateList.forEach { news ->
            NewsCard(
                newsUiState = news,
                navigateToWebView = navigateToWebView,
            )
            Spacer(modifier = Modifier.height(SPACING_MEDIUM.dp))
        }
    }
}

@Composable
private fun NewsColumnTitle(onViewAllClick: () -> Unit) {
    Row(verticalAlignment = Alignment.Bottom) {
        val news = stringResource(R.string.feature_home_news)
        Text(
            text = news,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier =
            Modifier
                .testTag(news)
                .semantics { contentDescription = news },
        )
        Spacer(modifier = Modifier.weight(1f))
        ViewAllButton(onClick = onViewAllClick)
    }
}

@Composable
private fun ViewAllButton(onClick: () -> Unit) {
    val label = stringResource(R.string.feature_home_view_all_news)
    Row(
        modifier = Modifier.clickable(
            onClick = onClick,
            // 處理用戶互動的來源，用於設定無水波紋效果。
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            SPACING_SMALL.dp,
        ),
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.outline,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            modifier =
            Modifier
                .testTag(label)
                .semantics { contentDescription = label },
        )
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = TtlIcons.ArrowForward,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
        )
    }
}

@ThemePreviews
@Composable
fun NewsColumPreview() {
    TtlTheme {
        Surface {
            NewsColumn(
                modifier = Modifier.padding(horizontal = SPACING_LARGE.dp),
                newsUiStateList = emptyList(),
                navigateToWebView = { _, _ -> },
                onViewAllClick = {},
            )
        }
    }
}
