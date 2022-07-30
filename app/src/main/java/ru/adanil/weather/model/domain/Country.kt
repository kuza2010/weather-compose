package ru.adanil.weather.model.domain

data class Country(
    val id: Int,
    val name: String,
    val iso3: String,
    val iso2: String,
    val capital: String?,
    val region: String?,
    val subregion: String?,
    val emoji: String,
    val emojiU: String,
)
