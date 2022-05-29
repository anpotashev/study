package ru.net.arh.study.exception

class TelegramException(override val message: String): RuntimeException(message) {
}