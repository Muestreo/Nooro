package com.kryptopass.nooro.core.network.model

import com.kryptopass.nooro.core.domain.entity.Condition
import com.kryptopass.nooro.core.domain.entity.Current
import com.kryptopass.nooro.core.domain.entity.Location
import com.kryptopass.nooro.core.domain.entity.Weather

fun WeatherResponse.toDomain(): Weather {
    return Weather(
        current = current?.toDomain(),
        location = location?.toDomain()
    )
}

private fun com.kryptopass.nooro.core.network.model.Condition.toDomain(): Condition {
    return Condition(
        code = code ?: 0,
        icon = icon ?: "",
        text = text ?: ""
    )
}

private fun com.kryptopass.nooro.core.network.model.Current.toDomain(): Current {
    return Current(
        condition = condition?.toDomain() ?: Condition(),
        feelslikeC = feelslikeC ?: 0.0,
        feelslikeF = feelslikeF ?: 0.0,
        humidity = humidity ?: 0,
        tempC = tempC ?: 0.0,
        tempF = tempF ?: 0.0,
        uv = uv ?: 0.0
    )
}

private fun com.kryptopass.nooro.core.network.model.Location.toDomain(): Location {
    return Location(
        country = country ?: "",
        name = name ?: "",
        region = region ?: ""
    )
}
