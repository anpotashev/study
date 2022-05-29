package ru.net.arh.study.service

interface QuestService {
    fun questNewWord(userId: Long, collectionId: Long): Long
    fun saveResult(userId: Long, wordId: Long, success: Boolean)
}