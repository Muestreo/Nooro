package com.kryptopass.nooro.core.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCondition(condition: ConditionEntity?): String? {
        return gson.toJson(condition)
    }

    @TypeConverter
    fun toCondition(json: String?): ConditionEntity? {
        return gson.fromJson(json, object : TypeToken<ConditionEntity>() {}.type)
    }
}