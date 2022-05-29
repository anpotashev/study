package ru.net.arh.study.service

import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service
import ru.net.arh.study.bh.dto.BhDto
import ru.net.arh.study.bh.service.ExampleServiceGrpc
import ru.net.arh.study.data.Example
import ru.net.arh.study.exception.NoDataFoundException

@Service
class ExampleServiceImpl : ExampleService {

    @GrpcClient("study-bh")
    private lateinit var exampleServiceBlockingStub: ExampleServiceGrpc.ExampleServiceBlockingStub

    override fun getAll(userId: Long, wordId: Long): List<Example> {
        return exampleServiceBlockingStub.getAll(
            BhDto.GetAllExamplesRq.newBuilder()
                .setUserId(userId)
                .setWordId(wordId)
                .build()
        )
            .asSequence()
            .map {
                Example(
                    id = it.id,
                    text = it.exampleText
                )
            }
            .toList()
    }

    override fun get(userId: Long, id: Long): Example {
        val example = exampleServiceBlockingStub.getById(
            BhDto.GetExampleRq.newBuilder()
                .setUserId(userId)
                .setExampleId(id)
                .build()
        )
        if (example.dataCase == BhDto.NullableExample.DataCase.NULL) {
            throw NoDataFoundException(
                "ROW_NOT_FOUND", "Запись не найдена"
            )
        }
        return Example(
            id = example.example.id,
            text = example.example.exampleText
        )
    }

    override fun create(userId: Long, wordId: Long, example: Example): Example {
        var createRs = exampleServiceBlockingStub.create(
            BhDto.CreateExampleRq.newBuilder()
                .setUserId(userId)
                .setWordId(wordId)
                .setExampleText(example.text)
                .build()
        )
        return get(userId, createRs.id)
    }

    override fun update(userId: Long, example: Example): Example {
        exampleServiceBlockingStub.edit(
            BhDto.EditExampleRq.newBuilder()
                .setUserId(userId)
                .setExampleId(example.id)
                .setExampleText(example.text)
                .build()
        )
        return get(userId, example.id)
    }

    override fun delete(userId: Long, id: Long) {
        exampleServiceBlockingStub.delete(
            BhDto.DeleteExampleRq.newBuilder()
                .setUserId(userId)
                .setExampleId(id)
                .build()
        )
    }
}