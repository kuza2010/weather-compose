package ru.adanil.weather.core.service

import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider(private val appContext: Context) {

    fun string(@StringRes stringRes: Int, vararg args: String): String {
        return appContext.getString(stringRes, args)
    }
}