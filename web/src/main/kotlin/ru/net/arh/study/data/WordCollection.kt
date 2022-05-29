package ru.net.arh.study.data

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("WORD_COLLECTION")
data class WordCollection(
    val id: Long = 0,
    val name: String
): Payload()
