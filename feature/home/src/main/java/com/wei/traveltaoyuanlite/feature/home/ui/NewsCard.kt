package com.wei.traveltaoyuanlite.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wei.traveltaoyuanlite.core.designsystem.component.ThemePreviews
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_EXTRA_SMALL
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_MEDIUM
import com.wei.traveltaoyuanlite.core.designsystem.theme.TtlTheme
import com.wei.traveltaoyuanlite.feature.home.NewsUiState

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    newsUiState: NewsUiState,
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = modifier.padding(all = SPACING_MEDIUM.dp)) {
            NewsPostedTime(postedTime = newsUiState.postedTime)
            Spacer(modifier = Modifier.height(SPACING_EXTRA_SMALL.dp))
            NewsName(name = newsUiState.name)
        }
    }
}

@Composable
private fun NewsPostedTime(postedTime: String) {
    Text(
        text = postedTime,
        style = MaterialTheme.typography.bodySmall,
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

@ThemePreviews
@Composable
fun NewsCardPreview() {
    TtlTheme {
        Surface {
            NewsCard(
                modifier = Modifier.padding(horizontal = SPACING_LARGE.dp),
                newsUiState = NewsUiState(
                    name = "水花祈福傳遞善念 走進龍岡體驗異域盛典 懷舊之夜重現金典",
                    postedTime = "2025/04/19",
                    tYWebsiteUrl = "travel.tycg.gov.tw/zh-tw/event/news/6356",
                ),
            )
        }
    }
}
