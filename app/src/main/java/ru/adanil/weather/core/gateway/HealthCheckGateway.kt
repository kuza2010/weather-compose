package ru.adanil.weather.core.gateway

import io.reactivex.Single
import retrofit2.http.GET

interface HealthCheckGateway {

    @GET("./")
    suspend fun healthCheck(): Single<Void>

}