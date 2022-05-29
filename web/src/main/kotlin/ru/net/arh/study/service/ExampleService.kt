package ru.net.arh.study.service

import ru.net.arh.study.data.Example

interface ExampleService {
    fun getAll(userId: Long, wordId: Long): List<Example>
    fun get(userId: Long, id: Long): Example
    fun create(userId: Long, wordId: Long, example: Example): Example
    fun update(userId: Long, example: Example): Example
    fun delete(userId: Long, id: Long)
}