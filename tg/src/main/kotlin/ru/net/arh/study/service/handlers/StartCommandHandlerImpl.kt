package ru.net.arh.study.service.handlers

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import ru.net.arh.study.service.TelegramAuthRepository
import ru.net.arh.study.service.TelegramAuthService
import java.io.Serializable

@Component("/start")
class StartCommandHandlerImpl(
    private val telegramAuthService: TelegramAuthService
) : CommandHandler {
    override fun onUpdate(update: Update): BotApiMethod<Serializable>? {
        val code = commandParam(update.message.text)
        val singleTimeCode = telegramAuthService.linkAuthDataAndChatIdAndGetSingleCode(update.message.chatId, code)
        return SendMessage.builder()
            .chatId(update.message.chatId.toString())
            .text("Use $singleTimeCode to login")
            .replyToMessageId(update.message.messageId)
            .build() as BotApiMethod<Serializable>
    }

    private fun commandParam(text: String): String {
        val indexOfSpace = text.indexOf(' ')
        return text.substring(indexOfSpace+1)
    }
}