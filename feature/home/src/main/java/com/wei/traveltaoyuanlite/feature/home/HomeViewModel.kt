package com.wei.traveltaoyuanlite.feature.home

import androidx.lifecycle.viewModelScope
import com.wei.traveltaoyuanlite.core.base.BaseViewModel
import com.wei.traveltaoyuanlite.core.data.repository.EventRepository
import com.wei.traveltaoyuanlite.core.data.repository.TravelRepository
import com.wei.traveltaoyuanlite.core.result.DataSourceResult
import com.wei.traveltaoyuanlite.core.result.asDataSourceResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TEST_LANG = "zh-tw"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    private val travelRepository: TravelRepository,
) : BaseViewModel<
    HomeViewAction,
    HomeViewState,
    >(HomeViewState()) {

    init {
        getEventNews()
        getTravelAttractions()
    }

    private fun getEventNews() {
        viewModelScope.launch {
            eventRepository.getPreviewEventNews(lang = TEST_LANG)
                .asDataSourceResult()
                .collect { result ->
                    val newsState = when (result) {
                        is DataSourceResult.Success -> {
                            NewsLoadingState.Finish(isSuccess = true) to
                                result.data.take(2).map { it.toNewsUiState() }
                        }

                        is DataSourceResult.Error -> {
                            NewsLoadingState.Finish(isSuccess = false) to emptyList()
                        }

                        DataSourceResult.Loading -> {
                            NewsLoadingState.Loading to null
                        }
                    }

                    updateState {
                        copy(
                            newsLoadingState = newsState.first,
                            newsUiStateList = newsState.second ?: newsUiStateList,
                        )
                    }
                }
        }
    }

    private fun getTravelAttractions() {
        viewModelScope.launch {
            travelRepository.getPreviewTravelAttractions(lang = TEST_LANG)
                .asDataSourceResult()
                .collect { result ->
                    val attractionsState = when (result) {
                        is DataSourceResult.Success -> {
                            AttractionsLoadingState.Finish(isSuccess = true) to
                                result.data.map { it.toAttractionUiState() }
                        }

                        is DataSourceResult.Error -> {
                            AttractionsLoadingState.Finish(isSuccess = false) to emptyList()
                        }

                        DataSourceResult.Loading -> {
                            AttractionsLoadingState.Loading to null
                        }
                    }

                    updateState {
                        copy(
                            attractionsLoadingState = attractionsState.first,
                            attractionsUiStateList = attractionsState.second
                                ?: attractionsUiStateList,
                        )
                    }
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
    }
}
