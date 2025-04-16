package com.wei.traveltaoyuanlite.feature.home

import com.wei.traveltaoyuanlite.core.base.Action
import com.wei.traveltaoyuanlite.core.base.State

sealed class HomeViewAction : Action {
    data object Call : HomeViewAction()
}

data class HomeViewState(
    val id: String = "",
) : State
