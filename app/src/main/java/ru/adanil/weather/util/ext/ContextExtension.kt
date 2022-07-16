package ru.adanil.weather.util.ext

import android.content.Context
import android.net.ConnectivityManager


val Context.connectivityManager: ConnectivityManager
    get() {
        return getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }