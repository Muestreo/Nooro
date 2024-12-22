package com.kryptopass.nooro.feature.search

import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.shared.common.state.CommonResultConverter
import javax.inject.Inject

class SearchConverter @Inject constructor() :
    CommonResultConverter<FetchWeatherByCityUseCase.Response, SearchModel>() {

    override fun convertSuccess(data: FetchWeatherByCityUseCase.Response): SearchModel {
        return SearchModel(
            condition = data.weather.current?.condition?.let { ConditionModel(it.icon) },
            name = data.weather.location?.name ?: "",
            tempF = data.weather.current?.tempF,
            tempC = data.weather.current?.tempC
        )
    }
}
