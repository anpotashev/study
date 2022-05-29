package ru.net.arh.study.service

import org.springframework.stereotype.Repository
import ru.net.arh.study.service.repositories.calls.*

@Repository
class TelegramAuthRepositoryImpl(
    private val findChatIdByAuthData: FindChatIdByAuthData,
    private val deleteTemporalAuthData: DeleteTemporalAuthData,
    private val saveCodeAndSingleTimeCode: SaveCodeAndSingleTimeCode,
    private val getChatIdByCode: GetChatIdByCode,
    private val getSingleTimeCode: GetSingleTimeCode,
    private val linkAuthDataWithChatId: LinkAuthDataWithChatId

) : TelegramAuthRepository {

    override fun getChatId(code: String, singleTimeCode: String): Long? {
        val params = hashMapOf(
            "p_code" to code,
            "p_single_time_code" to singleTimeCode
        )
        return findChatIdByAuthData.findObjectByNamedParam(params)
    }

    override fun deleteAuthData(code: String) {
        val delParams = hashMapOf(
            "p_code" to code
        )
        deleteTemporalAuthData.updateByNamedParam(delParams)
    }

    override fun saveCodeAndSingleTimeCode(code: String, singleTimeCode: String) {
        val params = hashMapOf(
            "p_code" to code,
            "p_single_time_code" to singleTimeCode,
        )
        saveCodeAndSingleTimeCode.updateByNamedParam(params)
    }

    override fun linkAuthDataWithChatId(chatId: Long, code: String) {
        val params = hashMapOf(
            "p_chat_id" to chatId,
            "p_code" to code
        )
        linkAuthDataWithChatId.updateByNamedParam(params)
    }

    override fun getSingleTimeCode(code: String): String? {
        val params = hashMapOf(
            "p_code" to code
        )
        return getSingleTimeCode.findObjectByNamedParam(params)
    }

    override fun getChatIdByCode(code: String): Long? {
        val params = hashMapOf(
            "p_code" to code
        )
        return getChatIdByCode.findObjectByNamedParam(params)
    }
}