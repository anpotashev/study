package ru.net.arh.study.data

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("WORD")
data class Word(
    val id: Long = 0,
    val original: String,
    val translated: String,
    val soundUrl: String?,
    val successAnswerCount: Int = 0,
    val errorAnswerCount: Int = 0
): Payload()
