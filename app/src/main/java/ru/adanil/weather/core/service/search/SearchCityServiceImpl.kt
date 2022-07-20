package ru.adanil.weather.core.service.search

import ru.adanil.weather.core.gateway.GeocodingGateway
import ru.adanil.weather.core.repository.CountryRepository
import ru.adanil.weather.model.domain.City
import javax.inject.Inject

class SearchCityServiceImpl @Inject constructor(
    private val geocodingGateway: GeocodingGateway,
    private val countryRepository: CountryRepository,
) : SearchCityService {

    override suspend fun findCityByName(query: String): List<City> {
        val cities = geocodingGateway.findCityByName(query, 15)
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