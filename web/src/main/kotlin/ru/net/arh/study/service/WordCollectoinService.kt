package ru.net.arh.study.service

import ru.net.arh.study.data.WordCollection

interface WordCollectoinService {
    fun getAll(userId: Long): List<WordCollection>
    fun get(userId: Long, id: Long): WordCollection
    fun create(userId: Long, collection: WordCollection): WordCollection
    fun update(userId: Long, collection: WordCollection): WordCollection
    fun delete(userId: Long, id: Long)
}