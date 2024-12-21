package com.kryptopass.nooro.feature.dashboard

import com.kryptopass.nooro.shared.common.state.UiAction

sealed class WeatherUiAction : UiAction {

    data class Load(val name: String) : WeatherUiAction()
}