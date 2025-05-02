package com.wei.traveltaoyuanlite.feature.attractiondetail

import com.wei.traveltaoyuanlite.core.base.Action
import com.wei.traveltaoyuanlite.core.base.State
import com.wei.traveltaoyuanlite.core.data.navigation.AttractionDetailNavArgs

sealed class AttractionDetailViewAction : Action

data class AttractionDetailViewState(
    val attractionDetailUiState: AttractionDetailNavArgs? = null,
) : State
