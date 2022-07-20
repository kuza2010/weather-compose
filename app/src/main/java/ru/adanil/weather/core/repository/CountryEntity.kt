package ru.adanil.weather.core.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.adanil.weather.model.domain.Country

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
) {
    fun toDomain(): Country {
        return Country(
            id = id,
            name = name,
            iso3 = iso3,
            iso2 = iso2,
            capital = capital,
            region = region,
            subregion = subregion,
            emoji = emoji,
            emojiU = emojiU,
        )
    }
}