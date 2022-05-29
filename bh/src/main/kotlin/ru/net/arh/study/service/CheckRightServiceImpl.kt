package ru.net.arh.study.service

import org.springframework.stereotype.Service
import ru.net.arh.study.exception.BhRowNotFoundException
import ru.net.arh.study.service.repositories.CheckRightRepository

@Service
class CheckRightServiceImpl(
    private val checkRightRepository: CheckRightRepository
): CheckRightService {
    override fun checkUserIdAndCollectionId(userId: Long, collectionId: Long) {
        if (!checkRightRepository.isUserIdAndCollectionIdExists(userId, collectionId)) {
            throw BhRowNotFoundException("NOT FOUND")
        }
    }

    override fun checkUserIdAndWordId(userId: Long, wordId: Long) {
        if (!checkRightRepository.isUserIdAndWordIdExists(userId, wordId)) {
            throw BhRowNotFoundException("NOT FOUND")
        }
    }

    override fun checkUserIdAndExampleId(userId: Long, exampleId: Long) {
        if (!checkRightRepository.isUserIdAndExampleIdExists(userId, exampleId)) {
            throw BhRowNotFoundException("NOT FOUND")
        }
    }
}