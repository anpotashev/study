package ru.net.arh.study.service

interface CheckRightService {

    fun checkUserIdAndCollectionId(userId: Long, collectionId: Long)
    fun checkUserIdAndWordId(userId: Long, wordId: Long)
    fun checkUserIdAndExampleId(userId: Long, exampleId: Long)

}