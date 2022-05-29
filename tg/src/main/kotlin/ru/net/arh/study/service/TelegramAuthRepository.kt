package ru.net.arh.study.service

interface TelegramAuthRepository {
    fun getChatId(code: String, singleTimeCode: String): Long?
    fun deleteAuthData(code: String)
    fun saveCodeAndSingleTimeCode(code: String, singleTimeCode: String)
    fun linkAuthDataWithChatId(chatId: Long, code: String)
    fun getSingleTimeCode(code: String): String?
    fun getChatIdByCode(code: String): Long?
}