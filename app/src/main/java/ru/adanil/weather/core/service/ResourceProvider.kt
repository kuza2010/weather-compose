package ru.adanil.weather.core.service

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val appContext: Context) {

    fun getString(@StringRes stringRes: Int): String {
        return appContext.getString(stringRes)
    }
}