package com.kryptopass.nooro.feature.home

import com.kryptopass.nooro.shared.common.state.UiSingleEvent

// NOTE: concrete UiSingleEvent, one-time event to navigate to Search Screen
sealed class HomeUiSingleEvent : UiSingleEvent {
    data class OpenSearchScreen(val city: String) : HomeUiSingleEvent()
}
