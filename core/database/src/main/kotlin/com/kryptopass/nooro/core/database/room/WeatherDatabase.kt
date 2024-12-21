package com.kryptopass.nooro.core.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ConditionEntity::class, WeatherEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(WeatherConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}