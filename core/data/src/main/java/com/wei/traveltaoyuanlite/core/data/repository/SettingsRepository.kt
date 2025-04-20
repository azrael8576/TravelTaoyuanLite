package com.wei.traveltaoyuanlite.core.data.repository

import com.wei.traveltaoyuanlite.core.AppLocale
import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {
    val languageFlow: StateFlow<AppLocale>
    fun setLanguage(languageCode: String)
}
