package ru.net.arh.study.data

data class BaseResponse(
    val errorData: ErrorData = ErrorData(),
    val payload: List<Payload> = emptyList()
)