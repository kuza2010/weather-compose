package ru.adanil.weather.ui.screens.pref.searching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.adanil.weather.core.repository.CityRepository
import ru.adanil.weather.core.service.search.SearchCityService
import ru.adanil.weather.model.domain.City
import javax.inject.Inject

data class SearchUiState(
    val searchQuery: String,
    val citiesThatMatchCriteria: List<City>
) {
    val hasResult =
        searchQuery.isEmpty() || searchQuery.isNotBlank() && citiesThatMatchCriteria.isNotEmpty()

    companion object {
        fun empty() = SearchUiState("", listOf())
    }
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCityService: SearchCityService,
    private val cityRepository: CityRepository,
) : ViewModel() {

    private var searchJob: Job? = null
    private val _uiState = MutableStateFlow(SearchUiState.empty())

    val uiState: StateFlow<SearchUiState>
        get() = _uiState

    init {
        viewModelScope.launch {
            _uiState.map { it.searchQuery }
                .distinctUntilChanged()
                .collect { query -> runCitySearch(query) }
        }
    }

    fun updateSearchQuery(value: String) {
        if (_uiState.value.searchQuery != value) {
            _uiState.update { it.copy(searchQuery = value) }
        }
    }

    fun searchCity() {
        runCitySearch(_uiState.value.searchQuery)
    }

    private fun runCitySearch(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            when (query.isBlank()) {
                true -> _uiState.update { SearchUiState.empty() }
                false -> {
                    delay(500)
                    val result = searchCityService.findCityByName(query)
                    _uiState.update { it.copy(citiesThatMatchCriteria = result) }
                }
            }
        }
    }

    fun selectCity(city: City) {
        viewModelScope.launch {
            // be aware that city is not selected
            cityRepository.insertCity(city.copy(isSelected = false))
            cityRepository.selectCity(city)
        }
    }
}
