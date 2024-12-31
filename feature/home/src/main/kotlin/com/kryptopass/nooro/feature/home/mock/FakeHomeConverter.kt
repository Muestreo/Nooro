package com.kryptopass.nooro.feature.home.mock

import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.feature.home.ConditionModel
import com.kryptopass.nooro.feature.home.HomeConverter
import com.kryptopass.nooro.feature.home.HomeModel

class FakeHomeConverter : HomeConverter() {
    override fun convertSuccess(data: FetchWeatherByCityUseCase.Response): HomeModel {
        return HomeModel(
            condition = ConditionModel(icon = "Mock Icon"),
            feelsLikeC = data.weather.current?.feelslikeC,
            feelsLikeF = data.weather.current?.feelslikeF,
            humidity = data.weather.current?.humidity,
            name = data.weather.location?.name,
            tempF = data.weather.current?.tempF,
            tempC = data.weather.current?.tempC,
            uv = data.weather.current?.uv
        )
    }
}
