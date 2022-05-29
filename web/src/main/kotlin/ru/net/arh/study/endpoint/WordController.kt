package ru.net.arh.study.endpoint

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.net.arh.study.data.Word
import ru.net.arh.study.security.data.TelegramUserDetails
import ru.net.arh.study.service.WordService
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/collection/{collectionId}/word")
class WordController(
    val wordService: WordService
) {

    @GetMapping
    fun getAll(@PathVariable("collectionId") collectionId: Long) =
        wordService.getAll(chatId(), collectionId)

    @GetMapping("/{id}")
    fun get(@PathVariable("id") wordId: Long) =
        wordService.get(chatId(), wordId)

    @PostMapping
    fun create(
        @PathVariable("collectionId") collectionId: Long,
        @PathParam("original") original: String,
        @PathParam("translated") translated: String,
        @PathParam("soundUrl") soundUrl: String?

    ) =
        wordService.create(
            userId = chatId(),
            wordCollectionId = collectionId,
            word = Word(
                original = original,
                translated = translated,
                soundUrl = soundUrl
            )
        )

    @PutMapping("/{id}")
    fun edit(
        @PathVariable("id") wordId: Long,
        @PathParam("original") original: String,
        @PathParam("translated") translated: String,
        @PathParam("soundUrl") soundUrl: String?
    ) =
        wordService.update(
            userId = chatId(),
            word = Word(
                id = wordId,
                original = original,
                translated = translated,
                soundUrl = soundUrl
            )
        )

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") wordId: Long) =
        wordService.delete(chatId(), wordId)

    private fun chatId() =
        (SecurityContextHolder.getContext().authentication.details as TelegramUserDetails)
            .chatId

}