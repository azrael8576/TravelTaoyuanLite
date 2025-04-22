package com.wei.traveltaoyuanlite.feature.home

import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.viewModelScope
import com.wei.traveltaoyuanlite.core.AppLocale
import com.wei.traveltaoyuanlite.core.base.BaseViewModel
import com.wei.traveltaoyuanlite.core.data.repository.EventRepository
import com.wei.traveltaoyuanlite.core.data.repository.SettingsRepository
import com.wei.traveltaoyuanlite.core.data.repository.TravelRepository
import com.wei.traveltaoyuanlite.core.result.DataSourceResult
import com.wei.traveltaoyuanlite.core.result.asDataSourceResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

// 等 setApplicationLocales 套用後再更新狀態
private const val LANGUAGE_SWITCH_DELAY = 1_000L

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val travelRepository: TravelRepository,
    private val settingsRepository: SettingsRepository,
) : BaseViewModel<
    HomeViewAction,
    HomeViewState,
    >(HomeViewState()) {

    init {
        observeAppLanguage()
        observeEventNews()
        observeTravelAttractions()
        checkAppLanguage()
    }

    private fun observeAppLanguage() {
        settingsRepository.languageFlow
            .onEach { lang ->
                updateState { copy(currentLanguage = lang) }
            }
            .launchIn(viewModelScope)
    }

    private fun observeEventNews() {
        settingsRepository.languageFlow
            .flatMapLatest { lang ->
                eventRepository
                    .getPreviewEventNews(lang = lang.apiArg)
                    .asDataSourceResult()
            }
            .onEach { result ->
                val (loadingState, items) = when (result) {
                    is DataSourceResult.Loading ->
                        NewsLoadingState.Loading to null

                    is DataSourceResult.Success ->
                        NewsLoadingState.Finish(isSuccess = true) to
                            result.data.take(2).map { it.toNewsUiState() }

                    is DataSourceResult.Error ->
                        NewsLoadingState.Finish(isSuccess = false) to emptyList()
                }
                updateState {
                    copy(
                        newsLoadingState = loadingState,
                        newsUiStateList = items ?: newsUiStateList,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeTravelAttractions() {
        settingsRepository.languageFlow
            .flatMapLatest { lang ->
                travelRepository
                    .getPreviewTravelAttractions(lang = lang.apiArg)
                    .asDataSourceResult()
            }
            .onEach { result ->
                val (loadingState, items) = when (result) {
                    is DataSourceResult.Loading ->
                        AttractionsLoadingState.Loading to null

                    is DataSourceResult.Success ->
                        AttractionsLoadingState.Finish(isSuccess = true) to
                            result.data.map { it.toAttractionUiState() }

                    is DataSourceResult.Error ->
                        AttractionsLoadingState.Finish(isSuccess = false) to emptyList()
                }
                updateState {
                    copy(
                        attractionsLoadingState = loadingState,
                        attractionsUiStateList = items ?: attractionsUiStateList,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun checkAppLanguage() {
        val appLocale = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        val systemLocale =
            Resources.getSystem().configuration.locales[0]?.language ?: "en"
        val languageCode = appLocale.ifEmpty { systemLocale }

        settingsRepository.setLanguage(languageCode)
    }

    private fun onSwitchLanguage(appLocale: AppLocale) {
        // 先切換系統語系
        val newLocales = LocaleListCompat.forLanguageTags(appLocale.code)
        AppCompatDelegate.setApplicationLocales(newLocales)

        // 等待系統生效後，再更新 repository，觸發 flow 重載
        viewModelScope.launch {
            delay(LANGUAGE_SWITCH_DELAY)
            settingsRepository.setLanguage(appLocale.code)
            updateState {
                copy(isLanguageSwitched = true)
            }
        }
    }

    /**
     * 處理用戶的 UI 操作，例如點擊一個按鈕。具體的實現將根據操作來更新狀態或發送事件。
     *
     * 通過 dispatch 統一進行事件的分發，有利於 View 與 ViewModel 間進一步解偶，
     * 同時也方便進行日誌分析與後續處理。
     *
     * @param action 用戶的 UI 操作。
     */
    override fun dispatch(action: HomeViewAction) {
        Timber.d("dispatch $action")
        when (action) {
            is HomeViewAction.SwitchLanguage -> onSwitchLanguage(action.appLocale)
        }
    }
}
