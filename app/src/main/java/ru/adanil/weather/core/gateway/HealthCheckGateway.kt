package ru.adanil.weather.core.gateway

import retrofit2.Response
import retrofit2.http.GET

interface HealthCheckGateway {

    @GET("./")
    suspend fun healthCheck(): Response<Unit>

}