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

    private val _messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    private val _messagesWithAction: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())

    val messages: StateFlow<List<Message>> get() = _messages.asStateFlow()
    val messagesWithAction: StateFlow<List<Message>> get() = _messagesWithAction.asStateFlow()


    fun showMessage(message: Message) {
        _messages.update { it.plus(message) }
    }

    fun messageActionLabelClicked(message: Message) {
        setMessageShown(message.id)
        _messagesWithAction.update { it.plus(message) }
    }


    fun setMessageShown(messageId: Long) {
        _messages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }

    fun serMessageActionPerformed(messageId: Long) {
        _messagesWithAction.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }

}