package ru.net.arh.study.endpoint

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.net.arh.study.data.Example
import ru.net.arh.study.security.data.TelegramUserDetails
import ru.net.arh.study.service.ExampleService
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/collection/{collectionId}/word/{wordId}/example")
class ExampleController(
    private val exampleService: ExampleService
) {

    @GetMapping
    fun getAll(@PathVariable("wordId") wordId: Long) =
        exampleService.getAll(chatId(), wordId)

    @GetMapping("/{id}")
    fun get(@PathVariable("id") exampleId: Long) =
        exampleService.get(chatId(), exampleId)

    @PostMapping
    fun create(
        @PathVariable("wordId") wordId: Long,
        @PathParam("text") text: String
    ) =
        exampleService.create(
            userId = chatId(),
            wordId = wordId,
            example = Example(text = text)
        )


    @PutMapping("/{id}")
    fun edit(
        @PathVariable("id") exampleId: Long,
        @PathParam("text") text: String
    ) =
        exampleService.update(
            userId = chatId(),
            example = Example(id = exampleId, text = text)
        )

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") exampleId: Long) =
        exampleService.delete(chatId(), exampleId)


    private fun chatId() =
        (SecurityContextHolder.getContext().authentication.details as TelegramUserDetails)
            .chatId

}