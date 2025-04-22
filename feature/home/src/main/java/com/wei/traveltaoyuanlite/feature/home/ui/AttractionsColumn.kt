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
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.core.designsystem.component.ThemePreviews
import com.wei.traveltaoyuanlite.core.designsystem.icon.TtlIcons
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.core.designsystem.theme.TtlTheme
import com.wei.traveltaoyuanlite.feature.home.R
import com.wei.traveltaoyuanlite.feature.home.ui.carousel.AttractionsCarousel

@Composable
fun AttractionsColumn(
    modifier: Modifier = Modifier,
    attractionsList: List<AttractionDetailNavArgs>,
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    onViewAllClick: () -> Unit,
    navigateToAttractionDetail: (AttractionDetailNavArgs) -> Unit,
) {
    Column(modifier = modifier) {
        AttractionsColumnTitle(onViewAllClick = onViewAllClick)
        Spacer(Modifier.height(SPACING_SMALL.dp))
        if (attractionsList.isNotEmpty()) {
            AttractionsCarousel(
                attractionsList = attractionsList,
                widthSizeClass = widthSizeClass,
                navigateToAttractionDetail = navigateToAttractionDetail,
            )
        }
    }
}

@Composable
private fun AttractionsColumnTitle(
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        val title = stringResource(R.string.feature_home_attractions)
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .testTag(title)
                .semantics { contentDescription = title },
        )
        Spacer(Modifier.weight(1f))
        ViewAllButton(onClick = onViewAllClick)
    }
}

@Composable
private fun ViewAllButton(onClick: () -> Unit) {
    val label = stringResource(R.string.feature_home_view_all_attractions)
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
                onViewAllClick = {},
                navigateToAttractionDetail = { },
            )
        }
    }
}

val sampleNames = listOf("天空步道", "大溪老街", "慈湖", "石門水庫", "小烏來")

val fakeAttractionsList = (1..5).map { i ->
    AttractionDetailNavArgs(
        id = i.toString(),
        tyWebsite = "",
        classes = listOf("自然生態", "文化古蹟"),
        name = sampleNames.getOrNull(i - 1) ?: "景點$i",
        description = "這是第 $i 號景點的描述",
        district = "區域$i",
        address = "地址$i",
        phone = "(02) 0000-000$i",
        openTime = "08:00–17:00",
        ticket = "NT\$${50 + i * 10}",
        remind = "參觀提醒$i",
        parking = "停車場$i",
        facilities = listOf("廁所", "停車場"),
        images = listOf(),
        links = listOf(),
    )
}
