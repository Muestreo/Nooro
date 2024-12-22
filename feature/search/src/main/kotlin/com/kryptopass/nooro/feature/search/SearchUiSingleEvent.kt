package com.kryptopass.nooro.feature.search

import com.kryptopass.nooro.shared.common.state.UiSingleEvent

// NOTE: concrete UiSingleEvent, one-time event to navigate back to Home Screen
sealed class SearchUiSingleEvent : UiSingleEvent {
    data class AddCityToCityList(val city: String) : SearchUiSingleEvent()
    data class BackToHomeScreen(val city: String) : SearchUiSingleEvent()
}
