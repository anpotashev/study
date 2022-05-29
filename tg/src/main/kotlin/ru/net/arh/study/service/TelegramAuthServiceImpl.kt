package ru.net.arh.study.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.net.arh.study.exception.TelegramException
import java.util.*

@Service
class TelegramAuthServiceImpl(
    private val telegramAuthRepository: TelegramAuthRepository
) : TelegramAuthService {


    @Value("\${telegram.botName}")
    private val botName: String = ""

    override fun startAuth(): Pair<String, String> {
        val code = UUID.randomUUID().toString()
        val singleTimeCode = (1..6).map{ ('0'..'9').random() }.joinToString("")
        telegramAuthRepository.saveCodeAndSingleTimeCode(code, singleTimeCode)
        return code to "https://t.me/$botName?start=${code}"
    }

    override fun linkAuthDataAndChatIdAndGetSingleCode(chatId: Long, code: String): String {
        telegramAuthRepository.getChatIdByCode(code)?.let {
            throw TelegramException("К переданному коду уже привязан чат")
        }
        telegramAuthRepository.linkAuthDataWithChatId(chatId, code)
        return telegramAuthRepository.getSingleTimeCode(code) ?: throw TelegramException("Не найден переданный код аворизации")
    }

    override fun finishAuth(code: String, singleTimeCode: String): Long? {
        val chatId = telegramAuthRepository.getChatId(code, singleTimeCode)
        telegramAuthRepository.deleteAuthData(code)
        return chatId
    }
}