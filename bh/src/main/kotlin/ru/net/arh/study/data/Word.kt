package ru.net.arh.study.data

data class Word(
    val original: String,
    val translated: String,
    val soundUrl: String?,
    val id: Long,
    val successAnswer: Int,
    val errorAnswer: Int
)
