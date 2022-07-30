package ru.adanil.weather.ui.screens.offline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.adanil.weather.core.service.healthCheck.HealthCheckService
import ru.adanil.weather.di.coroutine.IoDispatcher
import javax.inject.Inject


data class OfflineUiState(val isAvailable: Boolean)


// TODO: https://medium.com/@ffvanderlaan/navigation-in-jetpack-compose-using-viewmodel-state-3b2517c24dde
@HiltViewModel
class OfflineScreenViewModel @Inject constructor(
    private val healthCheckService: HealthCheckService,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState: MutableStateFlow<OfflineUiState> = MutableStateFlow(OfflineUiState(false))
    val uiState: StateFlow<OfflineUiState>
        get() = _uiState


    init {
        checkConnection()
    }


    private fun checkConnection() {
        viewModelScope.launch(context = defaultDispatcher) {
            while (!healthCheckService.isApiAvailable()) {
                delay(1000)
                Log.e("TESTIN", "checkConnection: not available...")
            }
            _uiState.update { it.copy(isAvailable = true) }
        }
    }

}