package ru.adanil.weather.ui.screens.pref

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.adanil.weather.R
import ru.adanil.weather.core.repository.CityRepository
import ru.adanil.weather.core.service.ResourceProvider
import ru.adanil.weather.model.City
import ru.adanil.weather.ui.Message
import ru.adanil.weather.ui.SnackBarManager
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
    private val coroutineScope: CoroutineScope,
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
                        cityRepository.insertCity(cityFromBucket)
                    }
                }
        }
    }


    fun deleteCity(city: City): Boolean {
        if (busy.compareAndSet(false, true)) {
            coroutineScope.launch {
                val snackbarMessageId = deleteCityWithMessage(city)
                delay(4000)
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


    private suspend fun deleteCityWithMessage(cityForRemoval: City): Long {
        val snackbarMessage = Message(
            resourceProvider.string(R.string.message_city_removed, cityForRemoval.name),
            resourceProvider.string(R.string.general_undo_caps),
        )
        chumBucket[snackbarMessage.id] = cityForRemoval
        SnackBarManager.showMessage(snackbarMessage)
        cityRepository.delete(cityForRemoval)

        return snackbarMessage.id
    }

}