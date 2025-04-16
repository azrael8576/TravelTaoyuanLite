package com.wei.traveltaoyuanlite.feature.home

import androidx.lifecycle.viewModelScope
import com.wei.traveltaoyuanlite.core.base.BaseViewModel
import com.wei.traveltaoyuanlite.core.data.repository.EventRepository
import com.wei.traveltaoyuanlite.core.data.repository.TravelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

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
            eventRepository.getEventNews(
                lang = "zh-tw",
                page = 1,
            ).collect { result ->
                Timber.e("getEventNews $result")
            }
        }
    }

    private fun getTravelAttractions() {
        viewModelScope.launch {
            travelRepository.getTravelAttractions(
                lang = "zh-tw",
                page = 1,
            ).collect { result ->
                Timber.e("getTravelAttractions $result")
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
//        TODO("Not yet implemented")
    }
}
