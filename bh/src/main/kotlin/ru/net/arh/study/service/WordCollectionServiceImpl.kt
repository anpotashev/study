package ru.net.arh.study.service

import org.springframework.stereotype.Service
import ru.net.arh.study.data.WordCollection
import ru.net.arh.study.service.repositories.WordCollectionRepository

@Service
class WordCollectionServiceImpl(
    private val wordCollectionRepository: WordCollectionRepository,
    private val checkRightService: CheckRightService
) : WordCollectionService {

    override fun create(userId: Long, name: String): Long {
        return wordCollectionRepository.create(userId, name)
    }

    override fun getWordCollection(userId: Long, wordCollectionId: Long) : WordCollection? {
        checkRightService.checkUserIdAndCollectionId(userId, wordCollectionId)
        return wordCollectionRepository.getWordCollection(wordCollectionId)
    }

    override fun getWordCollections(userId: Long) : List<WordCollection> {
        return wordCollectionRepository.getWordCollections(userId)
    }
    override fun edit(userId: Long, wordCollectionId: Long, name: String) {
        checkRightService.checkUserIdAndCollectionId(userId, wordCollectionId)
        wordCollectionRepository.update(wordCollectionId, name)
    }

    override fun delete(userId: Long, wordCollectionId: Long) {
        checkRightService.checkUserIdAndCollectionId(userId, wordCollectionId)
        wordCollectionRepository.delete(wordCollectionId)
    }
}