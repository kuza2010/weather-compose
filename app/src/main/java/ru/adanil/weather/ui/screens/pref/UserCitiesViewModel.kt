package ru.adanil.weather.ui.screens.pref

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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


    private fun refresh() {
        viewModelScope.launch {
//            cityRepository.getAll()
//                .collect { userCity ->
//                    _uiState.updateAndGet {
//                        _uiState.value.copy(cities = userCity)
//                    }
//                }
            _uiState.update {
                it.copy(
                    cities = listOf(
                        City("Moscow_1", true),
                        City("Moscow_2", false),
                        City("Moscow_3", false),
                        City("Moscow_4", false),
                        City("Moscow_5", false),
                        City("Moscow_6", false),
                        City("Moscow_7", false),
                        City("Moscow_8", false),
                        City("Moscow_9", false),
                        City("Moscow_10", false),
                        City("Moscow_11", false),
                        City("Moscow_12", false),
                        City("Moscow_13", false),
                        City("Moscow_14", false),
                        City("Moscow_15", false),
                        City("Moscow_long_name_very_long_yes", false),
                    ),
                    currentCity = City("A", true)
                )
            }
        }
    }

}