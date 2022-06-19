package ru.adanil.weather.core.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.adanil.weather.model.City

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "is_selected") val isSelected: Boolean
) {
    fun toDomain(): City = City(id, cityName, isSelected)
}