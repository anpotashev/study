package ru.net.arh.study.data

data class ErrorData(
    val success: Boolean = true,
    val errorCode: String? = null,
    val message: String? = null
)
