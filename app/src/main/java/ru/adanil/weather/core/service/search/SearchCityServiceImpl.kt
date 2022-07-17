package ru.adanil.weather.core.service.search

import ru.adanil.weather.core.gateway.GeocodingGateway
import javax.inject.Inject

class SearchCityServiceImpl @Inject constructor(
    private val geocodingGateway: GeocodingGateway
) : SearchCityService {
}