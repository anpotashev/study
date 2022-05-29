package ru.net.arh.study.data

import com.fasterxml.jackson.annotation.JsonTypeName

@JsonTypeName("EXAMPLE")
data class Example(
    val id: Long = 0,
    val text: String
): Payload()
