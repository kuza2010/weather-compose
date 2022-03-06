package ru.adanil.weather.core.gateway

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Inject

class RetrofitFactory @Inject constructor(
    val okHttpClient: OkHttpClient,
) {

    fun <T> create(baseUrl: String, clazz: Class<T>): T {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .baseUrl(baseUrl)
            .build()
            .create(clazz)
    }

}