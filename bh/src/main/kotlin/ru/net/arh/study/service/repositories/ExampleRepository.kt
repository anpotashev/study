package ru.net.arh.study.service.repositories

import ru.net.arh.study.data.Example

interface ExampleRepository {
    fun create(wordId: Long, text: String): Long
    fun update(exampleId: Long, text: String)
    fun getAll(wordId: Long): List<Example>
    fun get(exampleId: Long): Example?
    fun delete(exampleId: Long)
}