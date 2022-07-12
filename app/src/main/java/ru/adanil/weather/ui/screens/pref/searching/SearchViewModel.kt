package ru.adanil.weather.ui.screens.pref.searching

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.adanil.weather.core.repository.CityRepository
import ru.adanil.weather.core.service.ResourceProvider
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: MutableState<String>
        get() = _searchQuery

    fun updateSearchQuery(value: String) {
        _searchQuery.value = value
    }
}