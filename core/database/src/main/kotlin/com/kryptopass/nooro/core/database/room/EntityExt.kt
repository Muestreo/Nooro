package com.kryptopass.nooro.core.database.room

import com.kryptopass.nooro.core.domain.entity.Condition
import com.kryptopass.nooro.core.domain.entity.Current
import com.kryptopass.nooro.core.domain.entity.Location
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

fun WeatherEntity.toDomain(): Weather {
    return Weather(
        current = Current(
            condition = condition?.toDomain() ?: Condition(
                code = 0,
                icon = "",
                text = ""
            ),
            feelslikeC = feelsLikeC ?: 0.0,
            feelslikeF = feelsLikeF ?: 0.0,
            humidity = humidity ?: 0,
            region = region,
            tempC = tempC ?: 0.0,
            tempF = tempF ?: 0.0,
            uv = uv ?: 0.0
        ),
        location = Location(
            country = country,
            name = name,
            region = region
        )
    )
}

fun ConditionEntity.toDomain(): Condition {
    return Condition(
        code = code ?: 0,
        icon = icon.orEmpty(),
        text = text.orEmpty()
    )
}
