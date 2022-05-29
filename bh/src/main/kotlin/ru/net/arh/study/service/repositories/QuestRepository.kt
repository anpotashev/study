package ru.net.arh.study.service.repositories

interface QuestRepository {
    fun questNewWord(collectionId: Long): Long
    fun saveResult(wordId: Long, success: Boolean)
}