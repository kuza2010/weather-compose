package ru.adanil.weather.extension

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ru.adanil.weather.dagger.Factory

inline fun <reified T : ViewModel> ComponentActivity.viewModel(
    noinline create: (stateHandle: SavedStateHandle) -> T
) = viewModels<T> {
    Factory(this, create)
}