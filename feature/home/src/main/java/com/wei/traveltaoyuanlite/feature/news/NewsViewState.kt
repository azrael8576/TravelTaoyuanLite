package com.wei.traveltaoyuanlite.feature.news

import com.wei.traveltaoyuanlite.core.base.Action
import com.wei.traveltaoyuanlite.core.base.State
import com.wei.traveltaoyuanlite.core.model.data.EventNews

sealed class NewsViewAction : Action

data class NewsViewState(
    val newsLoadingState: NewsLoadingState = NewsLoadingState.Idle,
    val newsUiStateList: List<NewsUiState> = emptyList(),
) : State {
    val loadingFinish: Boolean
        get() = newsLoadingState == NewsLoadingState.Finish(isSuccess = true)
}

sealed interface NewsLoadingState {
    data object Idle : NewsLoadingState
    data object Loading : NewsLoadingState
    data class Finish(
        val isSuccess: Boolean,
    ) : NewsLoadingState
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
