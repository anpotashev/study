package ru.net.arh.study.service

import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service
import ru.net.arh.study.bh.dto.BhDto
import ru.net.arh.study.bh.service.WordServiceGrpc
import ru.net.arh.study.data.Word
import ru.net.arh.study.exception.NoDataFoundException

@Service
class WordServiceImpl : WordService {


    @GrpcClient("study-bh")
    private lateinit var wordServiceBlockingStub: WordServiceGrpc.WordServiceBlockingStub

    override fun getAll(userId: Long, wordCollectionId: Long): List<Word> {
        return wordServiceBlockingStub.getAll(
            BhDto.GetAllWordRq.newBuilder()
                .setUserId(userId)
                .setCollectionId(wordCollectionId)
                .build()
        )
            .asSequence()
            .map {
                Word(
                    id = it.id,
                    original = it.original,
                    translated = it.translated,
                    soundUrl = it.soundUrl.let { if (it.isNullOrBlank()) null else it },
                    successAnswerCount = it.successAnswer,
                    errorAnswerCount = it.errorAnswer
                )
            }
            .toList()
    }

    override fun get(userId: Long, id: Long): Word {
        val word = wordServiceBlockingStub.getById(
            BhDto.GetWordRq.newBuilder()
                .setUserId(userId)
                .setWordId(id)
                .build()
        )
        if (word.dataCase == BhDto.NullableWord.DataCase.NULL) {
            throw NoDataFoundException(
                "ROW_NOT_FOUND", "Запись не найдена"
            )
        }
        return Word(
            id = word.word.id,
            original = word.word.original,
            translated = word.word.translated,
            soundUrl = word.word.soundUrl.let { if (it.isNullOrBlank()) null else it },
            successAnswerCount = word.word.successAnswer,
            errorAnswerCount = word.word.errorAnswer
        )
    }

    override fun create(userId: Long, wordCollectionId: Long, word: Word): Word {
        val createRs = wordServiceBlockingStub.create(
            BhDto.CreateWordRq.newBuilder()
                .setUserId(userId)
                .setCollectionId(wordCollectionId)
                .setOriginal(word.original)
                .setTranslated(word.translated)
                .let { if (word.soundUrl != null) it.setSoundUrl(word.soundUrl) else it }
                .build())
        return get(userId, createRs.id)
    }

    override fun update(userId: Long, word: Word): Word {
        wordServiceBlockingStub.edit(
            BhDto.EditWordRq.newBuilder()
                .setUserId(userId)
                .setWordId(word.id)
                .setOriginal(word.original)
                .setTranslated(word.translated)
                .let { if (word.soundUrl != null) it.setSoundUrl(word.soundUrl) else it }
                .build())
        return get(userId, word.id)
    }

    override fun delete(userId: Long, id: Long) {
        wordServiceBlockingStub.delete(
            BhDto.DeleteWordRq.newBuilder()
                .setUserId(userId)
                .setWordId(id)
                .build()
        )
    }
}