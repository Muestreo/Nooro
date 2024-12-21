package com.kryptopass.nooro.core.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "condition") val condition: ConditionEntity? = ConditionEntity(id = 0, code = 0, icon = "", text = ""),
    @ColumnInfo(name = "country") val country: String? = "",
    @ColumnInfo(name = "feelsLikeC") val feelsLikeC: Double? = 0.0,
    @ColumnInfo(name = "feelsLikeF") val feelsLikeF: Double? = 0.0,
    @ColumnInfo(name = "humidity") val humidity: Int? = 0,
    @ColumnInfo(name = "region") val region: String? = "",
    @ColumnInfo(name = "tempC") val tempC: Double? = 0.0,
    @ColumnInfo(name = "tempF") val tempF: Double? = 0.0,
    @ColumnInfo(name = "uv") val uv: Double? = 0.0
)