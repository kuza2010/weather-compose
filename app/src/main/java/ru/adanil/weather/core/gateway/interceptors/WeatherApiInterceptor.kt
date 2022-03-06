package ru.adanil.weather.core.gateway.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class WeatherApiInterceptor(private val appid: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request()
        val newUrl = currentRequest.url.newBuilder()
            .addQueryParameter("appid", appid)
            .build()

        val newRequest = currentRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}