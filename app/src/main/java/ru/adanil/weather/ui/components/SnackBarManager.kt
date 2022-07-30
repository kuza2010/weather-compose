package ru.adanil.weather.ui.components

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.adanil.weather.ui.components.Message.Companion.empty
import java.util.UUID

data class Message(
    val message: String,
    val actionLabel: String? = null,
    val id: Long = UUID.randomUUID().mostSignificantBits,
) {
    companion object {
        val empty = Message(message = "Oops...", id = 666)
    }
}

object SnackBarManager {

    private val _messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    private val _messagesWithAction: MutableStateFlow<Message> = MutableStateFlow(empty)

    val messages: StateFlow<List<Message>> get() = _messages.asStateFlow()
    val messagesWithAction: StateFlow<Message> get() = _messagesWithAction.asStateFlow()

    fun showMessage(message: Message) {
        _messages.update { it.plus(message) }
    }

    fun messageActionLabelClicked(message: Message) {
        setMessageShown(message.id)
        _messagesWithAction.update { message }
    }

    fun setMessageShown(messageId: Long) {
        _messages.update { currentMessages ->
            currentMessages.filterNot { it.id == messageId }
        }
    }

    fun setMessageActionPerformed() {
        _messagesWithAction.update { empty }
    }
}
