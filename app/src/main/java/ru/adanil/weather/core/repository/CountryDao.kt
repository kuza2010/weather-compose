package ru.adanil.weather.core.repository

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountryDao {

    @Query("SELECT * FROM country WHERE iso2 IN (:isoCodes)")
    suspend fun getByIso2Codes(isoCodes: List<String>): List<CountryEntity>

    @Query("SELECT * FROM country WHERE id = :countryId")
    suspend fun getById(countryId: Int): CountryEntity
}
