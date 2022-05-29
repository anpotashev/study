package ru.net.arh.study.endpoint

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.net.arh.study.data.*
import ru.net.arh.study.data.request.*
import ru.net.arh.study.exception.ValidationException
import ru.net.arh.study.security.data.TelegramUserDetails
import ru.net.arh.study.service.ExampleService
import ru.net.arh.study.service.WordCollectoinService
import ru.net.arh.study.service.WordService

@RestController
@RequestMapping("/admin")
class AdminController(
    private val wordCollectoinService: WordCollectoinService,
    private val wordService: WordService,
    private val exampleService: ExampleService
) {

    @PostMapping
    fun action(@RequestBody request: BaseRequest): BaseResponse {
        return BaseResponse( payload =  when {
            request is GetAllRequest -> getAll(request)
            request is GetByIdRequest -> getById(request)
            request is CreateRequest -> create(request)
            request is UpdateRequest -> update(request)
            request is DeleteByIdRequest -> delete(request)
            else -> throw ValidationException("UNKOWN_REQUEST", "Неизвестный тип запроса")
        })
    }

    private fun getAll(request: GetAllRequest): List<Payload> {
        return when (request.type) {
            SubjectType.WORD_COLLECTION -> wordCollectoinService.getAll(chatId())
            SubjectType.WORD -> wordService.getAll(
                chatId(), request.collectionId
                    ?: throw ValidationException("VALIDATION_ERROR ", "Не переан параметр collectionId")
            )
            SubjectType.EXAMPLE -> exampleService.getAll(
                chatId(), request.wordId
                    ?: throw ValidationException("VALIDATION_ERROR ", "Не переан параметр wordId")
            )
        }
    }

    private fun getById(request: GetByIdRequest): List<Payload> {
        return listOf(
            when (request.type) {
                SubjectType.WORD_COLLECTION -> wordCollectoinService.get(chatId(), request.id)
                SubjectType.WORD -> wordService.get(chatId(), request.id)
                SubjectType.EXAMPLE -> exampleService.get(chatId(), request.id)
            }
        )
    }

    private fun create(request: CreateRequest): List<Payload> {
        return listOf(
            when (request.payload) {
                is WordCollection -> wordCollectoinService.create(chatId(), request.payload)
                is Word -> wordService.create(
                    userId = chatId(),
                    wordCollectionId = request.wordCollectionId ?: throw ValidationException(
                        "VALIDATION_ERROR ",
                        "Не переан параметр wordCollectionId"
                    ),
                    word = request.payload
                )
                is Example -> exampleService.create(
                    userId = chatId(),
                    wordId = request.wordId ?: throw ValidationException(
                        "VALIDATION_ERROR ",
                        "Не переан параметр wordId"
                    ),
                    example = request.payload
                )
                else -> throw ValidationException("UNKOWN_REQUEST", "Неизвестный тип запроса")
            }
        )
    }

    private fun update(request: UpdateRequest): List<Payload> {
        return listOf(
            when (request.payload) {
                is WordCollection -> wordCollectoinService.update(chatId(), request.payload)
                is Word -> wordService.update(
                    userId = chatId(),
                    word = request.payload
                )
                is Example -> exampleService.update(
                    userId = chatId(),
                    example = request.payload
                )
                else -> throw ValidationException("UNKOWN_REQUEST", "Неизвестный тип запроса")
            })
    }

    private fun delete(request: DeleteByIdRequest): List<Payload> {
        when (request.type) {
            SubjectType.WORD_COLLECTION -> wordCollectoinService.delete(chatId(), request.id)
            SubjectType.WORD -> wordService.delete(chatId(), request.id)
            SubjectType.EXAMPLE -> exampleService.delete(chatId(), request.id)
        }
        return emptyList()
    }

    private fun chatId() =
        (SecurityContextHolder.getContext().authentication.details as TelegramUserDetails)
            .chatId
}