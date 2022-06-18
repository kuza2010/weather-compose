package ru.adanil.weather.model.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "city_name") val cityName: String
)