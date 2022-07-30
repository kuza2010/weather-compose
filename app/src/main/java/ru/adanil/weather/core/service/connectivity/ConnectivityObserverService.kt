package ru.adanil.weather.core.service.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserverService {
    val observeInternetConnection: Flow<ConnectionStatus>
}
