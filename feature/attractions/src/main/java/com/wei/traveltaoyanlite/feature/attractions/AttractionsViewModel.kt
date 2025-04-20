package com.wei.traveltaoyanlite.feature.attractions

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.wei.traveltaoyuanlite.core.base.BaseViewModel
import com.wei.traveltaoyuanlite.core.data.repository.SettingsRepository
import com.wei.traveltaoyuanlite.core.data.repository.TravelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(
    private val travelRepository: TravelRepository,
    private val settingsRepository: SettingsRepository,
) : BaseViewModel<
    AttractionsViewAction,
    AttractionsViewState,
    >(AttractionsViewState()) {

    val pagingAttractionsFlow: StateFlow<PagingData<AttractionUiState>> =
        settingsRepository.languageFlow
            .flatMapLatest { lang ->
                travelRepository.getPagingTravelAttractions(lang = lang.apiArg)
                    .map { pagingData ->
                        pagingData.map { travelAttractions ->
                            travelAttractions.toAttractionUiState()
                        }
                    }
                    .cachedIn(viewModelScope)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PagingData.empty(),
            )

    /**
     * 處理用戶的 UI 操作，例如點擊一個按鈕。具體的實現將根據操作來更新狀態或發送事件。
     *
     * 通過 dispatch 統一進行事件的分發，有利於 View 與 ViewModel 間進一步解偶，
     * 同時也方便進行日誌分析與後續處理。
     *
     * @param action 用戶的 UI 操作。
     */
    override fun dispatch(action: AttractionsViewAction) {
    }
}
