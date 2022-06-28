package ru.adanil.weather.model

data class City(
    val id: String,
    val name: String,
    val isSelected: Boolean
): Comparable<City> {
    override fun compareTo(other: City): Int = name.compareTo(other.name)
}