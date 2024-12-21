package com.kryptopass.nooro.core.database.room

import com.kryptopass.nooro.core.domain.entity.Condition
import com.kryptopass.nooro.core.domain.entity.Weather

fun Weather.toEntity(): WeatherEntity {
    return WeatherEntity(
        condition = current?.condition?.toEntity(),
        country = location?.country.orEmpty(),
        feelsLikeC = current?.feelslikeC,
        feelsLikeF = current?.feelslikeF,
        humidity = current?.humidity,
        name = location?.name.orEmpty(),
        region = location?.region.orEmpty(),
        tempC = current?.tempC,
        tempF = current?.tempF,
        uv = current?.uv
    )
}

fun Condition.toEntity(): ConditionEntity {
    return ConditionEntity(
        code = code,
        icon = icon,
        text = text
    )
}
