package ru.net.arh.study.service.repositories

import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import ru.net.arh.study.data.Word
import ru.net.arh.study.data.WordCollection
import ru.net.arh.study.service.repositories.calls.word.*

@Repository
class WordRepositoryImpl (
    private val createWord: CreateWord,
    private val deleteWord: DeleteWord,
    private val getWord: GetWord,
    private val getWords: GetWords,
    private val updateWord: UpdateWord,
): WordRepository {

    override fun create(collectionId: Long,  original: String,  translated: String,  soundUrl: String): Long {
        val params = hashMapOf(
            "p_word_collection_id" to collectionId,
            "p_original" to original,
            "p_translated" to translated,
            "p_sound_url" to soundUrl
        )
        val keyHolder = GeneratedKeyHolder()
        createWord.updateByNamedParam(params, keyHolder)
        return keyHolder.key as Long
    }

    override fun update(wordId: Long, original: String, translated: String, soundUrl: String) {
        val params = hashMapOf(
            "p_id" to wordId,
            "p_original" to original,
            "p_translated" to translated,
            "p_sound_url" to soundUrl
        )
        updateWord.updateByNamedParam(params)
    }

    override fun getAll(collectionId: Long): List<Word> {
        val params = hashMapOf(
            "p_word_collection" to collectionId
        )
        return getWords.executeByNamedParam(params)
    }

    override fun get(wordId: Long): Word? {
        val params = hashMapOf(
            "p_id" to wordId
        )
        return getWord.findObjectByNamedParam(params)
    }

    override fun delete(wordId: Long) {
        val params = hashMapOf(
            "p_id" to wordId
        )
        deleteWord.updateByNamedParam(params)
    }
}