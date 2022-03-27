package ru.adanil.weather.model

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*


data class Message(val id: Long, @StringRes val messageId: Int)


object SnackBarManager {

    private val pMessages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val messages: StateFlow<List<Message>> get() = pMessages.asStateFlow()

    fun showMessage(@StringRes messageTextId: Int) {
        pMessages.update { currentMessages ->
            val newMessage = Message(
                id = UUID.randomUUID().mostSignificantBits,
                messageId = messageTextId
            )
            currentMessages.plus(newMessage)
        }
    }

    fun setMessageShown(messageId: Long) {
        pMessages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }

}