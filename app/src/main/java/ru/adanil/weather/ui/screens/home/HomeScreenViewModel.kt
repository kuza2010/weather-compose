package ru.adanil.weather.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.adanil.weather.core.repository.CityRepository
import ru.adanil.weather.core.service.connectivity.ConnectionStatus
import ru.adanil.weather.core.service.connectivity.ConnectivityObserverService
import ru.adanil.weather.core.service.weather.WeatherService
import ru.adanil.weather.model.domain.City
import ru.adanil.weather.model.domain.CurrentWeather
import javax.inject.Inject

data class HomeUiState(
    val city: City? = null,
    val weather: CurrentWeather? = null,
    val connectionStatus: ConnectionStatus? = null
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val weatherService: WeatherService,
    private val connectivityObserverService: ConnectivityObserverService,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState>
        get() = _uiState

    init {
        refresh()
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
                .map { userCity -> userCity to weatherService.currentWeather(userCity) }
                .collect { userCityAndWeather ->
                    _uiState.update {
                        it.copy(
                            city = userCityAndWeather.first,
                            weather = userCityAndWeather.second,
                        )
                    }
                }
        }
    }
}
