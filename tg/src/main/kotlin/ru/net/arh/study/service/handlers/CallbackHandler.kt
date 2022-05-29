package ru.net.arh.study.service.handlers

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update
import java.io.Serializable

interface CallbackHandler {
    fun onUpdate(update: Update): BotApiMethod<Serializable>?
}
