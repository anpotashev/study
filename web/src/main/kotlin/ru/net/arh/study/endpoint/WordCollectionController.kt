package ru.net.arh.study.endpoint

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.net.arh.study.data.WordCollection
import ru.net.arh.study.security.data.TelegramUserDetails
import ru.net.arh.study.service.WordCollectoinService
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/collection")
class WordCollectionController(
    private val wordCollectoinService: WordCollectoinService
) {

    @GetMapping()
    fun getAll() =
        wordCollectoinService.getAll(chatId())

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long) =
        wordCollectoinService.get(chatId(), id)

    @PostMapping()
    fun create(@PathParam("name") name: String) =
        wordCollectoinService.create(
            userId = chatId(),
            collection = WordCollection(name = name)
        )

    @PutMapping("/{id}")
    fun edit(@PathVariable("id") id: Long, @PathParam("name") name: String) =
        wordCollectoinService.update(
            userId = chatId(),
            collection = WordCollection(id = id, name = name)
        )

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) =
        wordCollectoinService.delete(chatId(), id)

    private fun chatId() =
        (SecurityContextHolder.getContext().authentication.details as TelegramUserDetails)
            .chatId

}