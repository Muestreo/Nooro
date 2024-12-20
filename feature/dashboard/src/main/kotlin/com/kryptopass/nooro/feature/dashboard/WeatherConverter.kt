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
            "data.location.name"
        )
    }
}