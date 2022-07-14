package ru.adanil.weather.core.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "city_name") val name: String
)