package ru.adanil.weather.core.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.adanil.weather.model.domain.City

@Entity(tableName = "cities")
data class CityEntity(
    @ColumnInfo(name = "city_id")@PrimaryKey val cityId: String,
    @ColumnInfo(name = "lat") val latitude: Double,
    @ColumnInfo(name = "lon") val longitude: Double,
    @ColumnInfo(name = "country_id") val countryId: Int,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "is_selected") val isSelected: Boolean,
) {
    fun toDomain(country: CountryEntity): City = City(
        name = cityName,
        latitude = latitude,
        longitude = longitude,
        isSelected = isSelected,
        country = country.toDomain()
    )
}
