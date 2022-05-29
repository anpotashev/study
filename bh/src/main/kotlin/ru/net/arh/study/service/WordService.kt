package ru.net.arh.study.service

import ru.net.arh.study.data.Word

interface WordService {
    fun create(userId: Long, collectionId: Long, original: String, translated: String, soundUrl: String): Long
    fun update(userId: Long, wordId: Long, original: String, translated: String, soundUrl: String)
    fun getAll(userId: Long, collectionId: Long): List<Word>
    fun get(userId: Long, wordId: Long): Word?
    fun delete(userId: Long, wordId: Long)
}