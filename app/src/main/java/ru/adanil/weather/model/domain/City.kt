package ru.adanil.weather.model.domain

import ru.adanil.weather.core.repository.CityEntity

data class City(
    val name: String,
    val latitude: Double,
    val country: Country,
    val longitude: Double,
    val isSelected: Boolean,
) : Comparable<City> {
    val id: String = "${name}_${latitude}_${longitude}"

    override fun compareTo(other: City): Int = name.compareTo(other.name)

    fun toEntity() = CityEntity(
        cityId = id,
        cityName = name,
        latitude = latitude,
        longitude = longitude,
        countryId = country.id,
        isSelected = isSelected
    )

}