package ru.net.arh.study.service

import org.springframework.stereotype.Service
import ru.net.arh.study.service.repositories.QuestRepository

@Service
class QuestServiceImpl(
    private val checkRightService: CheckRightService,
    private val questRepository: QuestRepository
): QuestService {
    override fun questNewWord(userId: Long, collectionId: Long): Long {
        checkRightService.checkUserIdAndCollectionId(userId, collectionId)
        return questRepository.questNewWord(collectionId)
    }

    override fun saveResult(userId: Long, wordId: Long, success: Boolean) {
        checkRightService.checkUserIdAndWordId(userId, wordId)
        questRepository.saveResult(wordId, success)
    }
}