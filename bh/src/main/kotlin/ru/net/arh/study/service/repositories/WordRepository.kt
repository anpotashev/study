package ru.net.arh.study.service.repositories

import ru.net.arh.study.data.Word

interface WordRepository {
    fun create(collectionId: Long, original: String, translated: String, soundUrl: String): Long
    fun update(wordId: Long, original: String, translated: String, soundUrl: String)
    fun getAll(collectionId: Long): List<Word>
    fun get(wordId: Long): Word?
    fun delete(wordId: Long)
}
