package ru.adanil.weather.core.service.search

import android.util.Log
import ru.adanil.weather.core.gateway.GeocodingGateway
import ru.adanil.weather.core.repository.CountryRepository
import ru.adanil.weather.model.domain.City
import javax.inject.Inject

class SearchCityServiceImpl @Inject constructor(
    private val geocodingGateway: GeocodingGateway,
    private val countryRepository: CountryRepository,
) : SearchCityService {

    override suspend fun findCityByName(query: String): List<City> {
        val cities = try {
            val response = geocodingGateway.findCityByName(query, 15)

            if (!response.isSuccessful && response.body() != null) {
                return emptyList()
            } else {
                response.body()!!
            }
        } catch (throwable: Throwable) {
            Log.e("SearchCityService", "findCityByName: unexpected error: '${throwable.message}'", throwable)
            return emptyList()
        }

        val isoCodes = cities.map { it.countryCode.uppercase() }
        val countries = countryRepository.getByIso2Codes(isoCodes).associateBy { it.iso2 }

        val resultList = mutableListOf<City>()

        cities.forEach { cityResponse ->
            val ourCountry = countries[cityResponse.countryCode.uppercase()]
            if (ourCountry != null) {
                resultList.add(
                    City(
                        isSelected = false,
                        country = ourCountry,
                        name = cityResponse.name,
                        latitude = cityResponse.latitude,
                        longitude = cityResponse.latitude,
                    )
                )
            }
        }

        return resultList
    }
}