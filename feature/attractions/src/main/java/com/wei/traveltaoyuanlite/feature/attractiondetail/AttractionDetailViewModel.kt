package com.wei.traveltaoyuanlite.feature.attractiondetail

import com.wei.traveltaoyuanlite.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttractionDetailViewModel @Inject constructor() : BaseViewModel<
    AttractionDetailViewAction,
    AttractionDetailViewState,
    >(AttractionDetailViewState()) {

    private fun onInit(action: AttractionDetailViewAction.Init) {
        updateState { copy(attractionDetailUiState = action.args) }
    }

    /**
     * 處理用戶的 UI 操作，例如點擊一個按鈕。具體的實現將根據操作來更新狀態或發送事件。
     *
     * 通過 dispatch 統一進行事件的分發，有利於 View 與 ViewModel 間進一步解偶，
     * 同時也方便進行日誌分析與後續處理。
     *
     * @param action 用戶的 UI 操作。
     */
    override fun dispatch(action: AttractionDetailViewAction) {
        when (action) {
            is AttractionDetailViewAction.Init -> {
                onInit(action)
            }
        }
    }
}
