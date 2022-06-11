package ru.adanil.weather.util.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.adanil.weather.core.service.connectivity.ConnectionStatus


val Context.connectivityManager: ConnectivityManager
    get() {
        return getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }