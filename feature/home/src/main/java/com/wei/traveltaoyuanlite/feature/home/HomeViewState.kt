package com.wei.traveltaoyuanlite.feature.home

import com.wei.traveltaoyuanlite.core.base.Action
import com.wei.traveltaoyuanlite.core.base.State
import com.wei.traveltaoyuanlite.core.model.data.EventNews
import com.wei.traveltaoyuanlite.core.model.data.TravelAttraction

sealed class HomeViewAction : Action {
    data object Call : HomeViewAction()
}

data class HomeViewState(
    val newsLoadingState: NewsLoadingState = NewsLoadingState.Idle,
    val attractionsLoadingState: AttractionsLoadingState = AttractionsLoadingState.Idle,
    val newsUiStateList: List<NewsUiState> = emptyList(),
    val attractionsUiStateList: List<AttractionUiState> = emptyList(),
) : State {
    val loadingFinish: Boolean
        get() = newsLoadingState == NewsLoadingState.Finish(isSuccess = true) &&
            attractionsLoadingState == AttractionsLoadingState.Finish(
                isSuccess = true,
            )
}

sealed interface NewsLoadingState {
    data object Idle : NewsLoadingState
    data object Loading : NewsLoadingState
    data class Finish(
        val isSuccess: Boolean,
    ) : NewsLoadingState
}

sealed interface AttractionsLoadingState {
    data object Idle : AttractionsLoadingState
    data object Loading : AttractionsLoadingState
    data class Finish(
        val isSuccess: Boolean,
    ) : AttractionsLoadingState
}

data class NewsUiState(
    val name: String,
    val postedTime: String,
    val tYWebsiteUrl: String,
)

data class AttractionUiState(
    val name: String,
    val imageUrl: String,
)

fun EventNews.toNewsUiState(): NewsUiState {
    val postedFormatted =
        posted.takeIf { it.isNotBlank() }?.split(" ")?.firstOrNull() ?: "yyyy/MM/dd"

    return NewsUiState(
        name = name,
        postedTime = postedFormatted,
        tYWebsiteUrl = tyWebsite,
    )
}

fun TravelAttraction.toAttractionUiState(): AttractionUiState {
    return AttractionUiState(
        name = name,
        imageUrl = if (images.isNotEmpty()) images.first().src else "",
    )
}
