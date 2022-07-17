package ru.adanil.weather.core.repository

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountryDao {

    @Query("SELECT * FROM country WHERE iso2 = :isoCode or iso3 = :isoCode")
    suspend fun getByIso(isoCode: String): CountryEntity?

}