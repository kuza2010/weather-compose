package ru.adanil.weather.core.repository

import ru.adanil.weather.model.domain.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepository @Inject constructor(
    private val countryDao: CountryDao
) {
    suspend fun getByIso2Codes(iso2Codes: List<String>): List<Country> {
        return countryDao.getByIso2Codes(iso2Codes).map { it.toDomain() }
    }
}
