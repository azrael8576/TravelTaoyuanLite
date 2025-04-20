package com.wei.traveltaoyuanlite.feature.news.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wei.traveltaoyuanlite.core.designsystem.component.ThemePreviews
import com.wei.traveltaoyuanlite.core.designsystem.component.coilImagePainter
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.TtlTheme
import com.wei.traveltaoyuanlite.feature.home.R
import com.wei.traveltaoyuanlite.feature.news.NewsUiState

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    newsUiState: NewsUiState,
    navigateToWebView: (String, String) -> Unit,
) {
    val newsWebViewTitle =
        stringResource(R.string.feature_home_news)
    Row(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth()
            .clickable(
                onClick = { navigateToWebView(newsUiState.tYWebsiteUrl, newsWebViewTitle) },
            ),
        horizontalArrangement = Arrangement.spacedBy(SPACING_LARGE.dp),
    ) {
        Box(
            modifier
                .width(100.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Image(
                modifier = Modifier.matchParentSize(),
                painter = coilImagePainter(imageData = newsUiState.imageUrl).painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
        ) {
            ClassText(text = newsUiState.classText)
            NewsName(name = newsUiState.name)
            Spacer(modifier = Modifier.weight(1f))
            NewsPostedTime(postedTime = newsUiState.postedTime)
        }
    }
}

@Composable
private fun ClassText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun NewsName(name: String) {
    Text(
        name,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
    )
}

@Composable
private fun NewsPostedTime(postedTime: String) {
    Text(
        text = postedTime,
        style = MaterialTheme.typography.bodySmall,
    )
}

@ThemePreviews
@Composable
fun NewsCardPreview() {
    TtlTheme {
        Surface {
            NewsCard(
                modifier = Modifier.padding(horizontal = SPACING_LARGE.dp),
                newsUiState = NewsUiState(
                    name = "水花祈福傳遞善念 走進龍岡體驗異域盛典 懷舊之夜重現金典",
                    classText = "新聞稿",
                    postedTime = "2025/04/19",
                    tYWebsiteUrl = "travel.tycg.gov.tw/zh-tw/event/news/6356",
                ),
                navigateToWebView = { _, _ -> },
            )
        }
    }
}
