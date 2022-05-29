package ru.net.arh.study.service.repositories

import ru.net.arh.study.data.WordCollection

interface WordCollectionRepository {

    fun create(userId: Long, name: String): Long
    fun getWordCollection(wordCollectionId: Long): WordCollection?
    fun getWordCollections(userId: Long): List<WordCollection>
    fun update(wordCollectionId: Long, name: String)
    fun delete(wordCollectionId: Long)

}
