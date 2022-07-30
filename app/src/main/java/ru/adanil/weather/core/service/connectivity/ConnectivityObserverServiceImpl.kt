package ru.adanil.weather.core.service.connectivity

import android.net.ConnectivityManager
import kotlinx.coroutines.flow.Flow
import ru.adanil.weather.util.ext.observeInternetConnectivityAsFlow
import javax.inject.Inject

class ConnectivityObserverServiceImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : ConnectivityObserverService {

    override val observeInternetConnection: Flow<ConnectionStatus>
        get() {
            return connectivityManager.observeInternetConnectivityAsFlow()
        }
}
