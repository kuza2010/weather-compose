package ru.adanil.weather.ui.screens.pref.searching

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.adanil.weather.core.gateway.GeocodingGateway
import ru.adanil.weather.model.CityResponse
import javax.inject.Inject


data class SearchUiState(
    val citiesThatMatchCriteria: List<CityResponse>
)


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val geocodingGateway: GeocodingGateway,
) : ViewModel() {

    private var searchJob: Job? = null
    private val _searchQuery = mutableStateOf("")
    private val _uiState = MutableStateFlow(SearchUiState(listOf()))

    val uiState: StateFlow<SearchUiState>
        get() = _uiState
    val searchQuery: MutableState<String>
        get() = _searchQuery


    fun updateSearchQuery(value: String) {
        if (_searchQuery.value != value) {
            _searchQuery.value = value
            runCitySearch(value)
        }
    }

    fun searchCity() {
        runCitySearch(_searchQuery.value)
    }


    private fun runCitySearch(query: String) {
        searchJob?.cancel()

        when (query.isBlank()) {
            true -> _uiState.update { SearchUiState(listOf()) }
            false -> searchJob = viewModelScope.launch {
                delay(500)
                val result = geocodingGateway.findCityByName(query)
                _uiState.update { SearchUiState(result) }
            }
        }
    }
}