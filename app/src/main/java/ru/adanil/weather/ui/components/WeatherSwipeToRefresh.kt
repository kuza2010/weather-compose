package ru.adanil.weather.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun WeatherSwipeToRefresh(
    isRefreshing: Boolean,
    isSwipeEnabled: Boolean = true,
    onRefreshRequested: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val state = rememberSwipeRefreshState(isRefreshing)
    SwipeRefresh(
        state = state,
        swipeEnabled = isSwipeEnabled,
        onRefresh = onRefreshRequested,
        refreshTriggerDistance = 120.dp,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    )
}
