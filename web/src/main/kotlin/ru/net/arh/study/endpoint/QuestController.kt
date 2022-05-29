package ru.net.arh.study.endpoint

import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import ru.net.arh.study.bh.dto.BhDto
import ru.net.arh.study.bh.service.QuestServiceGrpc
import ru.net.arh.study.bh.service.WordCollectionServiceGrpc
import ru.net.arh.study.security.data.TelegramUserDetails
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/collection/{collectionId}/quest")
class QuestController {

    @GrpcClient("study-bh")
    private lateinit var questServiceGrpc: QuestServiceGrpc.QuestServiceBlockingStub

    @GetMapping
    fun quest(@PathVariable("collectionId") collectionId: Long): Long {
        val questRs = questServiceGrpc.getNew(
            BhDto.QuestNewWordRq.newBuilder()
                .setUserId(chatId())
                .setCollectionId(collectionId)
                .build()
        )
        return questRs.id
    }

    @PutMapping("/{wordId}")
    fun saveResult(@PathVariable("wordId") wordId: Long, @PathParam("success") success: Boolean) {
        questServiceGrpc.saveQuestResult(BhDto.SaveQuestResultRq.newBuilder()
            .setUserId(chatId())
            .setWordId(wordId)
            .setSuccess(success)
            .build()
        )
    }

    private fun chatId() =
        (SecurityContextHolder.getContext().authentication.details as TelegramUserDetails)
            .chatId

}