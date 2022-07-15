package ru.adanil.weather.ui.screens.pref.searching

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.adanil.weather.core.gateway.GeocodingGateway
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val geocodingGateway: GeocodingGateway,
) : ViewModel() {

    private var searchJob: Job? = null
    private val _searchQuery = mutableStateOf("")
    val searchQuery: MutableState<String>
        get() = _searchQuery


    fun updateSearchQuery(value: String) {
        _searchQuery.value = value
        runCitySearch()
    }

    fun searchCity() {
        runCitySearch()
    }


    private fun runCitySearch() {
        searchJob?.cancel()
        val searchQuery = _searchQuery.value

        when (searchQuery.isBlank()) {
            true -> {
                // clean ui
            }
            false -> {
                searchJob = viewModelScope.launch {
                    delay(350)
                    val result = geocodingGateway.findCityByName(searchQuery)
                    Log.e("TESTIN", "searchCity: $result")
                }
            }
        }
    }
}