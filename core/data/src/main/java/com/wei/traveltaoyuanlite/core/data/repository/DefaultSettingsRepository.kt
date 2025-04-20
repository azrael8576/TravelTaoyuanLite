package com.wei.traveltaoyuanlite.core.data.repository

import com.wei.traveltaoyuanlite.core.AppLocale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of the [SettingsRepository].
 */
@Singleton
class DefaultSettingsRepository
@Inject constructor() : SettingsRepository {
    private val _languageFlow = MutableStateFlow(AppLocale.EN)

    override val languageFlow: StateFlow<AppLocale> = _languageFlow

    override fun setLanguage(languageCode: String) {
        _languageFlow.value = when {
            languageCode == AppLocale.EN.code -> AppLocale.EN
            languageCode == AppLocale.ZH_HANT_TW.code -> AppLocale.ZH_HANT_TW
            languageCode == AppLocale.ZH_HANS.code -> AppLocale.ZH_HANS
            languageCode == AppLocale.JA.code -> AppLocale.JA
            languageCode == AppLocale.KO.code -> AppLocale.KO
            // 任意 zh-Hant* 皆預設繁體
            languageCode.startsWith("zh-Hant") -> AppLocale.ZH_HANT_TW
            else -> AppLocale.EN
        }
    }
}
