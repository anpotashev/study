package ru.net.arh.study.service

interface TelegramAuthService {
    /**
     * Возвращает ссылку сообщение телеграм-бота с кодом авторизации
     */
    fun startAuth(): Pair<String, String>

    /**
     * Метод вызывается, когда пользователь переходит в телеграм по ссылке из метода startAuth
     * Связывает код и идентификатор чата
     */
    fun linkAuthDataAndChatIdAndGetSingleCode(chatId: Long, code: String): String

    /**
     * Завершает авторизацию. Удаляет авторизационные данные и возрващает chatId
     */
    fun finishAuth(code: String, singleTimeCode: String): Long?
}