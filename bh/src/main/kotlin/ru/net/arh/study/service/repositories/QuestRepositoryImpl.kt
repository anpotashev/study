package ru.net.arh.study.service.repositories

import org.springframework.stereotype.Repository
import ru.net.arh.study.service.repositories.calls.quest.QuestWord
import ru.net.arh.study.service.repositories.calls.quest.SaveQuestResult

@Repository
class QuestRepositoryImpl(
    private val saveQuestResult: SaveQuestResult,
    private val questWord: QuestWord
) : QuestRepository {

    override fun questNewWord(collectionId: Long): Long {
        val params = hashMapOf(
            "p_collection_id" to collectionId
        )
        val out = questWord.execute(params)
        if (!(out["p_err_code"] as String?).isNullOrBlank()) {
            throw RuntimeException(out["p_error"] as String)
        }
        return out["p_word_id"] as Long
    }

    override fun saveResult(wordId: Long, success: Boolean) {
        val params = hashMapOf(
            "p_id" to wordId,
            "p_success" to success
        )
        saveQuestResult.updateByNamedParam(params)
    }
}