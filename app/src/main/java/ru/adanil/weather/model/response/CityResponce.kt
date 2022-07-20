package ru.adanil.weather.model.response

import com.google.gson.annotations.SerializedName

data class CityResponse(
    val name: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("country") val countryCode: String,
    @SerializedName("local_names") val localNames: Map<String, String>?,
)