package com.kryptopass.nooro.feature.dashboard

import com.kryptopass.nooro.core.common.state.UiAction

sealed class WeatherUiAction : UiAction {

    data class Load(val city: String) : WeatherUiAction()
}