package ru.adanil.weather.core.gateway

import retrofit2.http.GET
import retrofit2.http.Query
import ru.adanil.weather.model.response.CityResponse

interface GeocodingGateway {

    @GET("direct")
    suspend fun findCityByName(
        @Query("q") cityIdentifier: String, // city name, state code, country code ISO 3166
        @Query("limit") limit: Int = 20,
    ): List<CityResponse>

}