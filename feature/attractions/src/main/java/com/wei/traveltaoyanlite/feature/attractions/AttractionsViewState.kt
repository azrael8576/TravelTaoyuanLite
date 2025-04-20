package com.wei.traveltaoyanlite.feature.attractions

import com.wei.traveltaoyuanlite.core.AppLocale
import com.wei.traveltaoyuanlite.core.base.Action
import com.wei.traveltaoyuanlite.core.base.State
import com.wei.traveltaoyuanlite.core.model.data.TravelAttraction

sealed class AttractionsViewAction : Action

data class AttractionsViewState(
    val currentLanguage: AppLocale = AppLocale.EN,
    val attractionsLoadingState: AttractionsLoadingState = AttractionsLoadingState.Idle,
    val attractionsUiStateList: List<AttractionUiState> = emptyList(),
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

data class AttractionUiState(
    val name: String,
    val imageUrl: String,
)

fun TravelAttraction.toAttractionUiState(): AttractionUiState {
    return AttractionUiState(
        name = name,
        imageUrl = if (images.isNotEmpty()) images.first().src else "",
    )
}
