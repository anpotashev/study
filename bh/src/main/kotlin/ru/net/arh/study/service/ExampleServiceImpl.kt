package ru.net.arh.study.service

import org.springframework.stereotype.Service
import ru.net.arh.study.data.Example
import ru.net.arh.study.service.repositories.ExampleRepository

@Service
class ExampleServiceImpl(
    private val exampleRepository: ExampleRepository,
    private val checkRightService: CheckRightService
) : ExampleService {
    override fun create(userId: Long, wordId: Long, text: String): Long {
        checkRightService.checkUserIdAndWordId(userId, wordId)
        return exampleRepository.create(wordId, text)
    }

    override fun update(userId: Long, exampleId: Long, text: String) {

        exampleRepository.update(exampleId, text)
    }

    override fun getAll(userId: Long, wordId: Long) : List<Example> {
        checkRightService.checkUserIdAndWordId(userId, wordId)
        return exampleRepository.getAll(wordId)
    }

    override fun get(userId: Long, exampleId: Long): Example? {
        checkRightService.checkUserIdAndExampleId(userId, exampleId)
        return exampleRepository.get(exampleId)
    }

    override fun delete(userId: Long, exampleId: Long) {
        checkRightService.checkUserIdAndExampleId(userId, exampleId)
        exampleRepository.delete(exampleId)
    }
}