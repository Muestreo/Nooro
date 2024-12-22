package com.kryptopass.nooro.feature.home

import com.kryptopass.nooro.shared.common.state.UiAction

sealed class HomeUiAction : UiAction {

    data class Load(val name: String) : HomeUiAction()
}