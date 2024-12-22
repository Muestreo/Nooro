package com.kryptopass.nooro.feature.search

import android.content.Context
import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.shared.common.state.CommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SearchConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<FetchWeatherByCityUseCase.Response, SearchModel>() {

    override fun convertSuccess(data: FetchWeatherByCityUseCase.Response): SearchModel {
        return SearchModel(
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