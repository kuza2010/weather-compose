package ru.adanil.weather.ui.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction.Companion.Search
import androidx.compose.ui.tooling.preview.Preview
import ru.adanil.weather.R
import ru.adanil.weather.ui.theme.WeatherTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WeatherSearchWidget(
    searchQuery: String,
    @StringRes placeholder: Int = R.string.general_search_placeholder,
    onBackIconClick: ((query: String) -> Unit)? = null,
    onSearchIconClick: ((query: String) -> Unit)? = null,
    onSearchQueryChange: ((query: String) -> Unit)? = null,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(
        backgroundColor = WeatherTheme.color.background
    ) {
        TextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
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
            onValueChange = { onSearchQueryChange?.invoke(it) },
            keyboardOptions = KeyboardOptions(imeAction = Search),
            keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        focusRequester.freeFocus()
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
        WeatherSearchWidget("hello", R.string.search_placeholder_city_search)
    }
}
