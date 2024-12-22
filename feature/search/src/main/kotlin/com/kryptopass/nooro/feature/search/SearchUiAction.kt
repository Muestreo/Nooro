package com.kryptopass.nooro.feature.search

import com.kryptopass.nooro.shared.common.state.UiAction

// NOTE: concrete UiAction specific for City Weather Card & search Done/Enter (search icon click)
sealed class SearchUiAction : UiAction {
    data class OnCityWeatherCardItemClick(val searchModel: SearchModel) : SearchUiAction()
    data class OnSearchBarEnterDoneItemClick(val city: String) : SearchUiAction()
}
