package ru.adanil.weather.ui.screens.pref

import android.util.Log
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.adanil.weather.core.repository.CityRepository
import ru.adanil.weather.model.City
import ru.adanil.weather.model.Message
import ru.adanil.weather.model.SnackBarManager
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

data class UserCitiesUiState(
    val cities: List<City> = listOf(),
    val currentCity: City? = cities.find { it.isSelected }
)

@HiltViewModel
class UserCitiesViewModel @Inject constructor(
    private val cityRepository: CityRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserCitiesUiState())
    private val chumBucket = ConcurrentHashMap<Long, City>()

    val uiState: StateFlow<UserCitiesUiState>
        get() = _uiState

    init {
        refresh()
    }


    fun deleteCity(city: City) {
        ProcessLifecycleOwner.get().lifecycleScope
            .launch {
                val snackbarMessageId = prepareCityToDelete(city)
                delay(4000)
                checkCity(city, snackbarMessageId)
                chumBucket.remove(snackbarMessageId)
            }
    }

    private fun refresh() {
        viewModelScope.launch {
            cityRepository.getAll()
                .collect { userCity ->
                    _uiState.update { current -> current.copy(cities = userCity) }
                }
        }
        viewModelScope.launch {
            SnackBarManager.messagesWithAction
                .filterNot { it == Message.empty }
                .collect { message ->
                    chumBucket.remove(message.id)?.let { cityFromBucket ->
                        _uiState.update { currentUIState ->
                            currentUIState.copy(cities = currentUIState.cities.plus(cityFromBucket))
                        }
                    }
                }
        }
    }

    private fun prepareCityToDelete(cityForRemoval: City): Long {
        synchronized(this) {
            val snackbarMessage = Message("City ... removed", "Undo")
            val newUIState = _uiState.updateAndGet { currentUIState ->
                val newCities = currentUIState.cities.minus(cityForRemoval)

                when (currentUIState.currentCity == cityForRemoval) {
                    true -> currentUIState.copy(
                        cities = newCities,
                        currentCity = newCities.firstOrNull()
                    )
                    false -> currentUIState.copy(
                        cities = newCities
                    )
                }
            }

            chumBucket[snackbarMessage.id] = cityForRemoval
            _uiState.update { newUIState }
            SnackBarManager.showMessage(snackbarMessage)

            return snackbarMessage.id
        }
    }

    private suspend fun checkCity(cityForCheck: City, snackbarMessageId: Long) {
        if (chumBucket.containsKey(snackbarMessageId)) {
            chumBucket.remove(snackbarMessageId)
            cityRepository.delete(cityForCheck)
        }
    }
}