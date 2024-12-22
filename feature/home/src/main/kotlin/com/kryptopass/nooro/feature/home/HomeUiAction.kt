package com.kryptopass.nooro.feature.home

import com.kryptopass.nooro.shared.common.state.UiAction

// NOTE: concrete UiAction specific for Search Done/Enter (search icon click)
sealed class HomeUiAction : UiAction {
    data class OnSearchBarEnterDoneItemClick(val city: String) : HomeUiAction()
}
