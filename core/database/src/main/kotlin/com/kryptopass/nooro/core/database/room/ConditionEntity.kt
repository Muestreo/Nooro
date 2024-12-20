package com.kryptopass.nooro.core.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "condition")
data class ConditionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = 0,
    @ColumnInfo(name = "code") val code: Int? = 0,
    @ColumnInfo(name = "icon") val icon: String? = "",
    @ColumnInfo(name = "text") val text: String? = ""
)