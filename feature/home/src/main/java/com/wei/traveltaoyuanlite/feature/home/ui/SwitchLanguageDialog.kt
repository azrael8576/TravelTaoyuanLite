package com.wei.traveltaoyuanlite.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.wei.traveltaoyuanlite.core.AppLocale
import com.wei.traveltaoyuanlite.core.designsystem.component.ThemePreviews
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_LARGE
import com.wei.traveltaoyuanlite.core.designsystem.theme.SPACING_SMALL
import com.wei.traveltaoyuanlite.core.designsystem.theme.TtlTheme
import com.wei.traveltaoyuanlite.core.designsystem.theme.shapes
import com.wei.traveltaoyuanlite.feature.home.R

@Composable
internal fun SwitchLanguageDialog(
    onDismissRequest: () -> Unit,
    currentLocale: AppLocale,
    onConfirmation: (AppLocale) -> Unit,
) {
    var selectedLocale by remember { mutableStateOf(currentLocale) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SPACING_LARGE.dp),
            shape = shapes.large,
        ) {
            Column(
                modifier = Modifier.padding(SPACING_LARGE.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.feature_home_choose_your_language),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(SPACING_LARGE.dp),
                )
                RadioButtonSingleSelection(
                    selectedLocale = selectedLocale,
                    onSelectedLocaleChange = { selectedLocale = it },
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onConfirmation(selectedLocale) },
                        modifier = Modifier.padding(SPACING_SMALL.dp),
                    ) {
                        Text(stringResource(R.string.feature_home_ok))
                    }
                }
            }
        }
    }
}

@Composable
internal fun RadioButtonSingleSelection(
    modifier: Modifier = Modifier,
    selectedLocale: AppLocale,
    onSelectedLocaleChange: (AppLocale) -> Unit,
) {
    val radioOptions = AppLocale.entries

    // Note: Modifier.selectableGroup() ensures correct accessibility behavior
    Column(modifier.selectableGroup()) {
        radioOptions.forEach { locale ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (locale == selectedLocale),
                        onClick = { onSelectedLocaleChange(locale) },
                        role = Role.RadioButton,
                    )
                    .padding(horizontal = SPACING_LARGE.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = (locale == selectedLocale),
                    onClick = null,
                )
                Text(
                    text = locale.text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = SPACING_LARGE.dp),
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun SwitchLanguageDialogPreview() {
    TtlTheme {
        SwitchLanguageDialog(
            onDismissRequest = {},
            currentLocale = AppLocale.EN,
            onConfirmation = {},
        )
    }
}
