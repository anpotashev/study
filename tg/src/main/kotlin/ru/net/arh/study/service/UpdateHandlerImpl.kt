package ru.net.arh.study.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update
import ru.net.arh.study.service.handlers.CommandHandler
import java.io.Serializable

@Service
class UpdateHandlerImpl(
    private val commandHandlers: Map<String, CommandHandler>,
//    private val callBackHandlers: Map<String, CallbackHandler>

    ) : UpdateHandler {
    override fun onUpdate(update: Update): BotApiMethod<Serializable>? =
            when {
                update.message?.isCommand ?: false -> commandHandlers.get(commandWithoutParameters(update.message.text))
                    ?.onUpdate(update)
                else -> null
            }


    private fun commandWithoutParameters(text: String): String {
        val indexOfSpace = text.indexOf(' ')
        return if (indexOfSpace == -1) text else text.substring(0, indexOfSpace)
    }
}