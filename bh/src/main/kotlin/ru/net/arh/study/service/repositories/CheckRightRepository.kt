package ru.net.arh.study.service.repositories

interface CheckRightRepository {

    fun isUserIdAndCollectionIdExists(userId: Long, collectionId: Long): Boolean
    fun isUserIdAndWordIdExists(userId: Long, wordId: Long): Boolean
    fun isUserIdAndExampleIdExists(userId: Long, exampleId: Long): Boolean

}