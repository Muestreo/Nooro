package com.kryptopass.nooro.feature.search.mock

import com.kryptopass.nooro.core.domain.usecase.FetchWeatherByCityUseCase
import com.kryptopass.nooro.feature.search.ConditionModel
import com.kryptopass.nooro.feature.search.SearchConverter
import com.kryptopass.nooro.feature.search.SearchModel

class FakeSearchConverter : SearchConverter() {
    override fun convertSuccess(data: FetchWeatherByCityUseCase.Response): SearchModel {
        return SearchModel(
            condition = ConditionModel(icon = "Mock Icon"),
            name = "Mock City",
            tempC = data.weather.current?.tempC,
            tempF = data.weather.current?.tempF,
        )
    }
}
