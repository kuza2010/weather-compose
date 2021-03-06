package ru.adanil.weather.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.adanil.weather.core.service.connectivity.ConnectionStatus
import ru.adanil.weather.core.service.connectivity.ConnectivityObserverService
import ru.adanil.weather.core.repository.CityRepository
import ru.adanil.weather.model.domain.City
import javax.inject.Inject


data class HomeUiState(
    val isLoading: Boolean = true,
    val cities: List<City>? = null,
    val connectionStatus: ConnectionStatus? = null,
    val currentCity : City? = cities?.find { it.isSelected }
)

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val connectivityObserverService: ConnectivityObserverService,
    private val cityRepository: CityRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState>
        get() = _uiState

    init {
        refresh()
    }


    private fun refresh() {
        viewModelScope.launch {
            cityRepository.getAll()
                .collect { userCity ->
                    _uiState.updateAndGet {
                        _uiState.value.copy(cities = userCity)
                    }
                }

            connectivityObserverService.observeInternetConnection
                .distinctUntilChanged()
                .collect { newConnectionStatus ->
                    _uiState.updateAndGet {
                        _uiState.value.copy(connectionStatus = newConnectionStatus)
                    }
                }
        }
    }
}