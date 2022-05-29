package ru.net.arh.study.exception

class ValidationException(val code: String, message: String): RuntimeException(message)