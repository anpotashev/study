package ru.net.arh.study.service

import org.springframework.stereotype.Service
import ru.net.arh.study.data.Word
import ru.net.arh.study.service.repositories.WordRepository

@Service
class WordServiceImpl(
    private val wordRepository: WordRepository,
    private val checkRightService: CheckRightService
) : WordService {

    override fun create(userId: Long, collectionId: Long, original: String, translated: String, soundUrl: String): Long {
        checkRightService.checkUserIdAndCollectionId(userId, collectionId)
        return wordRepository.create(collectionId, original, translated, soundUrl)
    }

    override fun update(userId: Long, wordId: Long, original: String, translated: String, soundUrl: String) {
        checkRightService.checkUserIdAndWordId(userId, wordId)
        wordRepository.update(wordId, original, translated, soundUrl)
    }


    override fun getAll(userId: Long, collectionId: Long): List<Word> {
        checkRightService.checkUserIdAndCollectionId(userId, collectionId)
        return wordRepository.getAll(collectionId)
    }

    override fun get(userId: Long, wordId: Long): Word? {
        checkRightService.checkUserIdAndWordId(userId, wordId)
        return wordRepository.get(wordId)
    }

    override fun delete(userId: Long, wordId: Long) {
        checkRightService.checkUserIdAndWordId(userId, wordId)
        wordRepository.delete(wordId)
    }
}