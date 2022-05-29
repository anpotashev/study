package ru.net.arh.study.exception

class NoDataFoundException(val code: String, message: String): RuntimeException(message)