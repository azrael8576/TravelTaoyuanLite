package com.wei.traveltaoyuanlite.feature.attractiondetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.core.designsystem.icon.TtlIcons
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_EXTRA_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_EXTRA_SMALL
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_MEDIUM
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.core.designsystem.theme.shapes
import com.wei.traveltaoyuanlite.feature.attractions.R

@Composable
fun AttractionDescriptionColumn(
    uiStates: AttractionDetailNavArgs,
    onAddressClick: () -> Unit,
    onPhoneClick: () -> Unit,
    onWebSiteClick: (String, String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(vertical = SPACING_LARGE.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SPACING_LARGE.dp),
    ) {
        DistrictRow(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            district = uiStates.district,
        )

        Text(
            text = uiStates.description,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        InfoItemCard(
            icon = TtlIcons.Ticket,
            text = uiStates.ticket,
        )

        InfoItemCard(
            icon = TtlIcons.Location,
            text = uiStates.address,
            onClick = onAddressClick,
        )

        InfoItemCard(
            icon = TtlIcons.Phone,
            text = uiStates.phone,
            onClick = onPhoneClick,
        )

        InfoItemCard(
            icon = TtlIcons.Public,
            text = stringResource(R.string.feature_attractions_official_website),
            onClick = { onWebSiteClick(uiStates.tyWebsite, uiStates.name) },
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp),
        ) {
            items(uiStates.facilities.size) { index ->
                FacilityCard(text = uiStates.facilities[index])
            }
        }

        RemindCard(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = uiStates.remind,
        )
    }
}

@Composable
private fun InfoItemCard(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    onClick: (() -> Unit)? = null,
) {
    if (text.isBlank()) return
    val cardModifier = modifier.clip(shape = shapes.extraLarge)
    if (onClick != null) {
        Card(
            modifier = cardModifier,
            onClick = onClick,
        ) {
            CardContent(icon, text)
        }
    } else {
        Card(
            modifier = cardModifier,
        ) {
            CardContent(icon, text)
        }
    }
}

@Composable
private fun CardContent(
    icon: ImageVector?,
    text: String,
) {
    Row(
        modifier = Modifier
            .padding(
                horizontal = SPACING_LARGE.dp,
                vertical = SPACING_MEDIUM.dp,
            ),
        horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun FacilityCard(
    text: String,
    modifier: Modifier = Modifier,
) {
    if (text.isBlank()) return
    Card(
        modifier = modifier
            .clip(shape = shapes.extraLarge),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = SPACING_LARGE.dp,
                    vertical = SPACING_MEDIUM.dp,
                ),
            horizontalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = text)
        }
    }
}

@Composable
private fun RemindCard(
    modifier: Modifier = Modifier,
    text: String,
) {
    if (text.isBlank()) return
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SPACING_LARGE.dp),
    ) {
        HorizontalDivider(modifier = Modifier.height(2.dp))
        val cardModifier = modifier
            .fillMaxWidth()
            .clip(shape = shapes.extraLarge)
        Card(
            modifier = cardModifier,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SPACING_EXTRA_LARGE.dp),
                verticalArrangement = Arrangement.spacedBy(SPACING_SMALL.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = TtlIcons.Info,
                    contentDescription = null,
                )
                Text(
                    text = text,
                )
            }
        }
    }
}

@Composable
private fun DistrictRow(
    modifier: Modifier = Modifier,
    district: String,
) {
    if (district.isBlank()) return
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(SPACING_EXTRA_SMALL.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = TtlIcons.Landscape,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = district,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
