package com.kryptopass.nooro.core.database.room

import com.kryptopass.nooro.core.domain.entity.Condition
import com.kryptopass.nooro.core.domain.entity.Current
import com.kryptopass.nooro.core.domain.entity.Location
import com.kryptopass.nooro.core.domain.entity.Weather

fun Weather.toEntity(): WeatherEntity {
    return WeatherEntity(
        region = location?.region ?: "",
        condition = current?.condition?.toEntity(),
        feelsLikeC = current?.feelslikeC,
        feelsLikeF = current?.feelslikeF,
        humidity = current?.humidity,
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

fun WeatherEntity.toDomain(): Weather {
    return Weather(
        current = Current(
            condition = condition?.toDomain(),
            feelslikeC = feelsLikeC,
            feelslikeF = feelsLikeF,
            humidity = humidity,
            region = region,
            tempC = tempC,
            tempF = tempF,
            uv = uv
        ),
        location = Location(
            region = region
        )
    )
}

fun ConditionEntity.toDomain(): Condition {
    return Condition(
        code = code,
        icon = icon,
        text = text
    )
}
