package com.kryptopass.nooro.core.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false) val region: String,
    @ColumnInfo(name = "condition") val condition: ConditionEntity?,
    @ColumnInfo(name = "feelsLikeC") val feelsLikeC: Double?,
    @ColumnInfo(name = "feelsLikeF") val feelsLikeF: Double?,
    @ColumnInfo(name = "humidity") val humidity: Int?,
    @ColumnInfo(name = "tempC") val tempC: Double?,
    @ColumnInfo(name = "tempF") val tempF: Double?,
    @ColumnInfo(name = "uv") val uv: Double?
)