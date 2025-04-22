package com.wei.traveltaoyuanlite.feature.home

import com.wei.traveltaoyuanlite.core.AppLocale
import com.wei.traveltaoyuanlite.core.base.Action
import com.wei.traveltaoyuanlite.core.base.State
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs
import com.wei.traveltaoyuanlite.core.model.data.EventNews

sealed class HomeViewAction : Action {
    data class SwitchLanguage(val appLocale: AppLocale) : HomeViewAction()
}

data class HomeViewState(
    val currentLanguage: AppLocale = AppLocale.EN,
    val newsLoadingState: NewsLoadingState = NewsLoadingState.Idle,
    val attractionsLoadingState: AttractionsLoadingState = AttractionsLoadingState.Idle,
    val newsUiStateList: List<NewsUiState> = emptyList(),
    val attractionsUiStateList: List<AttractionDetailNavArgs> = emptyList(),
    val isLanguageSwitched: Boolean = false,
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
    val classText: String,
    val postedTime: String,
    val tYWebsiteUrl: String,
    val imageUrl: String = "",
)

fun EventNews.toNewsUiState(): NewsUiState {
    val postedFormatted =
        posted.takeIf { it.isNotBlank() }?.split(" ")?.firstOrNull() ?: "yyyy/MM/dd"

    return NewsUiState(
        name = name,
        classText = if (classes.isNotEmpty()) classes[0] else "",
        postedTime = postedFormatted,
        tYWebsiteUrl = tyWebsite,
        imageUrl = if (images.isNotEmpty()) images[0].src else "",
    )
}
