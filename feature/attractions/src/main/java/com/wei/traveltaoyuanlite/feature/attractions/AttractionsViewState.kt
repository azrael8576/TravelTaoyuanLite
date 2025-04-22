package com.wei.traveltaoyuanlite.feature.attractions

import com.wei.traveltaoyuanlite.core.AppLocale
import com.wei.traveltaoyuanlite.core.base.Action
import com.wei.traveltaoyuanlite.core.base.State
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs

sealed class AttractionsViewAction : Action

data class AttractionsViewState(
    val currentLanguage: AppLocale = AppLocale.EN,
    val attractionsLoadingState: AttractionsLoadingState = AttractionsLoadingState.Idle,
    val attractionUiStateList: List<AttractionDetailNavArgs> = emptyList(),
) : State {
    val loadingFinish: Boolean
        get() =
            attractionsLoadingState == AttractionsLoadingState.Finish(
                isSuccess = true,
            )
}

sealed interface AttractionsLoadingState {
    data object Idle : AttractionsLoadingState
    data object Loading : AttractionsLoadingState
    data class Finish(
        val isSuccess: Boolean,
    ) : AttractionsLoadingState
}
