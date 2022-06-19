package ru.adanil.weather.ui.screens.pref

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import ru.adanil.weather.core.repository.CityRepository
import ru.adanil.weather.model.City
import javax.inject.Inject

data class UserCitiesUiState(
    val cities: List<City> = listOf(),
    val currentCity: City? = cities.find { it.isSelected }
)

@HiltViewModel
class UserCitiesViewModel @Inject constructor(
    private val cityRepository: CityRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UserCitiesUiState> =
        MutableStateFlow(UserCitiesUiState())
    val uiState: StateFlow<UserCitiesUiState>
        get() = _uiState

    init {
        refresh()
    }


    fun deleteCity(city: City) {
        viewModelScope.launch {
            cityRepository.delete(city)
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            cityRepository.getAll().collect { userCity ->
                _uiState.updateAndGet {
                    _uiState.value.copy(cities = userCity)
                }
            }
        }
    }

}