package ru.adanil.weather.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.adanil.weather.R
import ru.adanil.weather.core.repository.CityRepository
import ru.adanil.weather.core.service.ResourceProvider
import ru.adanil.weather.core.service.connectivity.ConnectionStatus
import ru.adanil.weather.core.service.connectivity.ConnectivityObserverService
import ru.adanil.weather.core.service.weather.WeatherService
import ru.adanil.weather.model.domain.City
import ru.adanil.weather.model.domain.CurrentWeather
import ru.adanil.weather.model.domain.Forecast
import ru.adanil.weather.model.domain.ResponseWrapper.GenericError
import ru.adanil.weather.model.domain.ResponseWrapper.NetworkError
import ru.adanil.weather.model.domain.ResponseWrapper.Success
import ru.adanil.weather.ui.components.Message
import ru.adanil.weather.ui.components.SnackBarManager
import javax.inject.Inject

data class HomeUiState(
    val city: City? = null,
    val loading: Boolean = true,
    val forecast: Forecast? = null,
    val weather: CurrentWeather? = null,
    val connectionStatus: ConnectionStatus? = null,
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherService: WeatherService,
    private val resourceProvider: ResourceProvider,
    private val connectivityObserverService: ConnectivityObserverService,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState>
        get() = _uiState

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing

    init {
        refresh()
    }

    fun refreshWeather() {
        viewModelScope.launch {
            val currentCity = cityRepository.getUserSelectedCity().firstOrNull()
            if (currentCity != null) {
                _isRefreshing.update { true }
                when (val response = weatherService.currentWeatherWithForecast(currentCity)) {
                    is Success -> _uiState.update {
                        it.copy(
                            weather = response.value.first,
                            forecast = response.value.second
                        )
                    }
                    is GenericError,
                    is NetworkError -> {
                        SnackBarManager.showMessageAtTheTop(
                            Message(resourceProvider.string(R.string.error_update_weather))
                        )
                    }
                }
                _isRefreshing.update { false }
            }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            connectivityObserverService.observeInternetConnection
                .distinctUntilChanged()
                .collect { newConnectionStatus ->
                    _uiState.update {
                        it.copy(connectionStatus = newConnectionStatus)
                    }
                }
        }
        viewModelScope.launch {
            cityRepository.getUserSelectedCity()
                .distinctUntilChanged()
                .retrieveWeather()
                .collect { userCityAndWeather ->
                    val weather = userCityAndWeather.second
                    _uiState.update {
                        it.copy(
                            loading = false,
                            weather = weather?.first,
                            forecast = weather?.second,
                            city = userCityAndWeather.first
                        )
                    }
                }
        }
    }

    private fun Flow<City?>.retrieveWeather(): Flow<Pair<City?, Pair<CurrentWeather, Forecast>?>> =
        map { userCity ->
            when (userCity) {
                null -> null to null
                else -> {
                    when (val response = weatherService.currentWeatherWithForecast(userCity)) {
                        is Success -> userCity to response.value
                        is GenericError,
                        is NetworkError -> {
                            SnackBarManager.showMessageAtTheTop(
                                Message(resourceProvider.string(R.string.error_fetch_weather))
                            )
                            userCity to null
                        }
                    }
                }
            }
        }
}
