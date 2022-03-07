package ru.adanil.weather.core.gateway

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET

interface HealthCheckGateway {

    @GET("./")
    fun healthCheck(): Completable

}