package ru.net.arh.study.service

import ru.net.arh.study.data.WordCollection

interface WordCollectionService {
    fun create(userId: Long, name: String): Long
    fun getWordCollection(userId: Long, wordCollectionId: Long): WordCollection?
    fun getWordCollections(userId: Long): List<WordCollection>
    fun edit(userId: Long, wordCollectionId: Long, name: String)
    fun delete(userId: Long, wordCollectionId: Long)
}