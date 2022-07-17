package ru.adanil.weather.core.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val iso3: String,
    val iso2: String,
    val capital: String?,
    val region: String?,
    val subregion: String?,
    val emoji: String,
    val emojiU: String,
)