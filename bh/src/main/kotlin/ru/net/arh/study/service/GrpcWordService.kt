package ru.net.arh.study.service

import com.google.protobuf.Empty
import com.google.protobuf.NullValue
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import ru.net.arh.study.bh.dto.BhDto
import ru.net.arh.study.bh.service.*
import ru.net.arh.study.data.Word

@GrpcService
class GrpcWordService(
    private val wordService: WordService
): WordServiceGrpc.WordServiceImplBase() {
    override fun create(request: BhDto.CreateWordRq, responseObserver: StreamObserver<BhDto.IdResponse>) {
        val id = wordService.create(request.userId, request.collectionId, request.original, request.translated, request.soundUrl)
        with(responseObserver) {
            onNext(BhDto.IdResponse.newBuilder().setId(id).build())
            onCompleted()
        }
    }

    override fun getAll(request: BhDto.GetAllWordRq, responseObserver: StreamObserver<BhDto.Word>) {
        with(responseObserver) {
            wordService.getAll(request.userId, request.collectionId)
                .forEach { onNext(toDtoWord(it)) }
            onCompleted()
        }
    }

    override fun getById(request: BhDto.GetWordRq, responseObserver: StreamObserver<BhDto.NullableWord>) {
        val word = wordService.get(request.userId, request.wordId)
        with(responseObserver) {
            onNext(toNullableWord(word))
            onCompleted()
        }
    }

    override fun edit(request: BhDto.EditWordRq, responseObserver: StreamObserver<Empty>) {
        wordService.update(request.userId, request.wordId, request.original, request.translated, request.soundUrl)
        empty(responseObserver)
    }

    override fun delete(request: BhDto.DeleteWordRq, responseObserver: StreamObserver<Empty>) {
        wordService.delete(request.userId, request.wordId)
        empty(responseObserver)
    }

    private fun empty(responseObserver: StreamObserver<Empty>) {
        with(responseObserver) {
            onNext(Empty.getDefaultInstance())
            onCompleted()
        }
    }

    private fun toDtoWord(word: Word) =
        BhDto.Word.newBuilder()
            .setOriginal(word.original)
            .setTranslated(word.translated)
            .setSoundUrl(word.soundUrl)
            .setId(word.id)
            .setSuccessAnswer(word.successAnswer)
            .setErrorAnswer(word.errorAnswer)
            .build()

    private fun toNullableWord(word: Word?) =
        word?.let {
            BhDto.NullableWord.newBuilder()
                .setWord(toDtoWord(word))
                .build()
        }?: BhDto.NullableWord.newBuilder()
            .setNull(NullValue.NULL_VALUE)
            .build()

}