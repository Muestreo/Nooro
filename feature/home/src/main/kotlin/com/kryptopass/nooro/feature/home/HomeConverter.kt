package com.kryptopass.nooro.feature.home

import android.content.Context
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.shared.common.state.CommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HomeConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<FetchWeatherByCityUseCase.Response, HomeModel>() {

    override fun convertSuccess(data: FetchWeatherByCityUseCase.Response): HomeModel {
        return HomeModel(
            condition = data.weather.current?.condition?.let { ConditionModel(it.icon) },
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
