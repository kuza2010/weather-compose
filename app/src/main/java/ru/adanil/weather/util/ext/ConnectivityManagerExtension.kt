package ru.adanil.weather.util.ext

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

val ConnectivityManager.currentConnectivityState: ConnectionStatus
    get() {
        val hasCapabilityWithInternet = activeNetwork
            ?.let { network ->
                getNetworkCapabilities(network)
                    ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            ?: false

        return when (hasCapabilityWithInternet) {
            true -> ConnectionStatus.CONNECTED
            false -> ConnectionStatus.DISCONNECTED
        }
    }

fun ConnectivityManager.observeInternetConnectivityAsFlow(): Flow<ConnectionStatus> {
    return callbackFlow {
        trySend(currentConnectivityState)

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(ConnectionStatus.CONNECTED)
            }

            override fun onLost(network: Network) {
                trySend(ConnectionStatus.DISCONNECTED)
            }

            override fun onUnavailable() {
                trySend(ConnectionStatus.DISCONNECTED)
            }
        }


        registerNetworkCallback(networkRequest, callback)

        awaitClose {
            unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()
}