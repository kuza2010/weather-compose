package ru.adanil.weather.core.service.search

import ru.adanil.weather.model.domain.City

interface SearchCityService {
    suspend fun findCityByName(query: String): List<City>
}
