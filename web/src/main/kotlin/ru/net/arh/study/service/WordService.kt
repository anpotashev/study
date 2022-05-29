package ru.net.arh.study.service

import ru.net.arh.study.data.Word

interface WordService {
    fun getAll(userId: Long, wordCollectionId: Long): List<Word>
    fun get(userId: Long, id: Long): Word
    fun create(userId: Long, wordCollectionId: Long, word: Word): Word
    fun update(userId: Long, word: Word): Word
    fun delete(userId: Long, id: Long)
}