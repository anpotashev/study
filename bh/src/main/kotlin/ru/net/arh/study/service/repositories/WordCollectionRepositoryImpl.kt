package ru.net.arh.study.service.repositories

import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import ru.net.arh.study.data.WordCollection
import ru.net.arh.study.service.repositories.calls.wordcollection.*

@Repository
class WordCollectionRepositoryImpl(
    private val getWordCollections: GetWordCollections,
    private val getWordCollection: GetWordCollection,
    private val createWordCollection: CreateWordCollection,
    private val deleteWordCollection: DeleteWordCollection,
    private val updateWordCollection: UpdateWordCollection
) : WordCollectionRepository {

    override fun create(userId: Long, name: String): Long {
        val params = hashMapOf(
            "p_user_id" to userId,
            "p_name" to name
        )
        val keyHolder = GeneratedKeyHolder()
        createWordCollection.updateByNamedParam(params, keyHolder)
        return keyHolder.key as Long
    }

    override fun getWordCollection(wordCollectionId: Long): WordCollection? {
        val params = hashMapOf(
            "p_id" to wordCollectionId
        )
        return getWordCollection.findObjectByNamedParam(params)
    }

    override fun getWordCollections(userId: Long): List<WordCollection> {
        val params = hashMapOf(
            "p_user_id" to userId
        )
        return getWordCollections.executeByNamedParam(params)
    }

    override fun update(wordCollectionId: Long, name: String) {
        val params = hashMapOf(
            "p_id" to wordCollectionId,
            "p_name" to name
        )
        updateWordCollection.updateByNamedParam(params)
    }

    override fun delete(wordCollectionId: Long) {
        val params = hashMapOf(
            "p_id" to wordCollectionId
        )
        deleteWordCollection.updateByNamedParam(params)
    }

}