package ru.net.arh.study.service

import ru.net.arh.study.data.Example

interface ExampleService {
    fun create(userId: Long, wordId: Long, text: String): Long
    fun update(userId: Long, exampleId: Long, text: String)
    fun getAll(userId: Long, wordId: Long): List<Example>
    fun get(userId: Long, exampleId: Long): Example?
    fun delete(userId: Long, exampleId: Long)
}