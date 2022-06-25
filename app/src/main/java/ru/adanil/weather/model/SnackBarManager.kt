package ru.adanil.weather.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*


data class Message(
    val message: String,
    val actionLabel: String? = null,
    val id: Long = UUID.randomUUID().mostSignificantBits,
)


object SnackBarManager {

    private val pMessages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val messages: StateFlow<List<Message>> get() = pMessages.asStateFlow()

    fun showMessage(message: Message) {
        pMessages.update { it.plus(message) }
    }

    fun setMessageShown(messageId: Long) {
        pMessages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }

}