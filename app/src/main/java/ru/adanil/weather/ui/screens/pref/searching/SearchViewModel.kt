package ru.adanil.weather.ui.screens.pref.searching

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: MutableState<String>
        get() = _searchQuery

    fun updateSearchQuery(value: String) {
        _searchQuery.value = value
    }
}