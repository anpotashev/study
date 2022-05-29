package ru.net.arh.study.service.repositories

import org.springframework.stereotype.Repository
import ru.net.arh.study.service.repositories.calls.checkrights.IsUserIdAndCollectionIdExists
import ru.net.arh.study.service.repositories.calls.checkrights.IsUserIdAndExampleIdExists
import ru.net.arh.study.service.repositories.calls.checkrights.IsUserIdAndWordIdExists

@Repository
class CheckRightRepositoryImpl(
    private val isUserIdAndCollectionIdExists: IsUserIdAndCollectionIdExists,
    private val isUserIdAndWordIdExists: IsUserIdAndWordIdExists,
    private val isUserIdAndExampleIdExists: IsUserIdAndExampleIdExists
) : CheckRightRepository {
    override fun isUserIdAndCollectionIdExists(userId: Long, collectionId: Long): Boolean {
        val params = hashMapOf(
            "p_user_id" to userId,
            "p_id" to collectionId
        )
        return isUserIdAndCollectionIdExists.findObjectByNamedParam(params)?: throw RuntimeException("Shit happens")
    }

    override fun isUserIdAndWordIdExists(userId: Long, wordId: Long): Boolean {
        val params = hashMapOf(
            "p_user_id" to userId,
            "p_id" to wordId
        )
        return isUserIdAndWordIdExists.findObjectByNamedParam(params)?: throw RuntimeException("Shit happens")
    }

    override fun isUserIdAndExampleIdExists(userId: Long, exampleId: Long): Boolean {
        val params = hashMapOf(
            "p_user_id" to userId,
            "p_id" to exampleId
        )
        return isUserIdAndExampleIdExists.findObjectByNamedParam(params)?: throw RuntimeException("Shit happens")
    }
}