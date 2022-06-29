package ru.adanil.weather.ui.screens.pref

import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.adanil.weather.R
import ru.adanil.weather.core.repository.CityRepository
import ru.adanil.weather.core.service.ResourceProvider
import ru.adanil.weather.model.City
import ru.adanil.weather.model.Message
import ru.adanil.weather.model.SnackBarManager
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

data class UserCitiesUiState(
    private val _cities: SortedSet<City> = sortedSetOf(),
    val currentCity: City? = _cities.find { it.isSelected }
) {
    constructor(notSortedCities: Collection<City>) : this(notSortedCities.toSortedSet())

    val cities: List<City> = _cities.toList()
}

@HiltViewModel
class UserCitiesViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserCitiesUiState())
    private val chumBucket = ConcurrentHashMap<Long, City>()
    private val busy = AtomicBoolean(false)

    val uiState: StateFlow<UserCitiesUiState>
        get() = _uiState

    init {
        viewModelScope.launch {
            cityRepository.getAll()
                .collect { userCity -> _uiState.update { UserCitiesUiState(userCity) } }
        }
        viewModelScope.launch {
            SnackBarManager.messagesWithAction
                .filterNot { it == Message.empty }
                .collect { message ->
                    busy.compareAndSet(true, false)
                    chumBucket.remove(message.id)?.let { cityFromBucket ->
                        _uiState.update { currentUIState ->
                            UserCitiesUiState(currentUIState.cities.plus(cityFromBucket))
                        }
                    }
                }
        }
    }


    fun deleteCity(city: City): Boolean {
        if (busy.compareAndSet(false, true)) {
            ProcessLifecycleOwner.get().lifecycleScope
                .launch {
                    val snackbarMessageId = prepareCityToDelete(city)
                    delay(4000)
                    checkCity(city, snackbarMessageId)
                    chumBucket.remove(snackbarMessageId)
                    busy.set(false)
                }
            return true
        }
        return false
    }

    fun selectCity(city: City) {
        viewModelScope.launch {
            cityRepository.selectCity(city)
        }
    }


    private fun prepareCityToDelete(cityForRemoval: City): Long {
        synchronized(this) {
            val snackbarMessage = Message(
                resourceProvider.string(R.string.message_city_removed, cityForRemoval.name),
                resourceProvider.string(R.string.general_undo_caps),
            )
            chumBucket[snackbarMessage.id] = cityForRemoval
            SnackBarManager.showMessage(snackbarMessage)
            _uiState.update { currentUIState ->
                UserCitiesUiState(currentUIState.cities.minus(cityForRemoval))
            }

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