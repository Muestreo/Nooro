package com.kryptopass.nooro.feature.dashboard

import android.content.Context
import com.kryptopass.nooro.core.common.state.CommonResultConverter
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WeatherConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<FetchWeatherByCityUseCase.Response, WeatherModel>() {

    override fun convertSuccess(data: FetchWeatherByCityUseCase.Response): WeatherModel {
        return WeatherModel(
            region = data.weather.location?.region,
            tempF = data.weather.current?.tempF,
            tempC = data.weather.current?.tempC,
            condition = data.weather.current?.condition?.let { ConditionModel(it.code, it.icon, it.text) },
            humidity = data.weather.current?.humidity,
            uv = data.weather.current?.uv,
            feelsLikeF = data.weather.current?.feelslikeF,
            feelsLikeC = data.weather.current?.feelslikeC
        )
    }
}