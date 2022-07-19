package ru.adanil.weather.model.domain

import ru.adanil.weather.core.repository.CityEntity

data class City(
    val id: String,
    val name: String,
    val isSelected: Boolean
) : Comparable<City> {

    override fun compareTo(other: City): Int = name.compareTo(other.name)

    fun toEntity() = CityEntity(
        id = id,
        cityName = name,
        isSelected = isSelected,
    )

}