package ru.adanil.weather.util.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import ru.adanil.weather.core.service.connectivity.ConnectionStatus
import ru.adanil.weather.util.ext.connectivityManager
import ru.adanil.weather.util.ext.currentConnectivityState
import ru.adanil.weather.util.ext.observeInternetConnectivityAsFlow

@Composable
fun connectivityState(): State<ConnectionStatus> {
    val connectivityManager = LocalContext.current.connectivityManager

    return connectivityManager.observeInternetConnectivityAsFlow()
        .collectAsState(initial = connectivityManager.currentConnectivityState)
}