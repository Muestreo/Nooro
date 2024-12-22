package com.kryptopass.nooro.feature.search

import com.kryptopass.nooro.shared.common.state.UiAction

sealed class SearchUiAction : UiAction {
    data class Load(val name: String) : SearchUiAction()
}