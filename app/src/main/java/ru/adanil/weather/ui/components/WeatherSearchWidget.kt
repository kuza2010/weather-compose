package ru.adanil.weather.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import ru.adanil.weather.R
import ru.adanil.weather.ui.theme.WeatherTheme

@Composable
fun WeatherSearchWidget(
    searchQuery: String,
    @StringRes placeholder: Int = R.string.general_search_placeholder,
    onBackIconClick: ((query: String) -> Unit)? = null,
    onSearchIconClick: ((query: String) -> Unit)? = null,
    onSearchQueryChange: ((query: String) -> Unit)? = null,
) {
    val focusRequester = remember { FocusRequester() }
    TopAppBar(
        backgroundColor = WeatherTheme.color.background
    ) {
        TextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .focusable(true)
                .focusRequester(focusRequester),
            textStyle = WeatherTheme.typography.h6,
            placeholder = {
                Text(
                    text = stringResource(placeholder),
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            },
            leadingIcon = {
                IconButton(
                    onClick = { onBackIconClick?.invoke(searchQuery) }
                ) {
                    WeatherIconMedium(
                        tint = contentColorFor(WeatherTheme.color.background),
                        painter = rememberVectorPainter(Icons.Filled.ArrowBack),
                        contentDescription = "Navigate back"
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = { onSearchIconClick?.invoke(searchQuery) }
                ) {
                    WeatherIconMedium(
                        tint = contentColorFor(WeatherTheme.color.background),
                        painter = rememberVectorPainter(Icons.Filled.Search),
                        contentDescription = "Tap to search"
                    )
                }
            },
            value = searchQuery,
            onValueChange = { onSearchQueryChange?.invoke(it.trim()) }
        )
    }
    
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewOfflineActivity() {
    WeatherTheme() {
        WeatherSearchWidget("hello", R.string.placeholder_city_search)
    }
}