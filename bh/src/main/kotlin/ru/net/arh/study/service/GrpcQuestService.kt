package ru.net.arh.study.service

import com.google.protobuf.Empty
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import ru.net.arh.study.bh.dto.BhDto
import ru.net.arh.study.bh.service.QuestServiceGrpc

@GrpcService
class GrpcQuestService(
    private val questService: QuestService
): QuestServiceGrpc.QuestServiceImplBase() {
    override fun getNew(request: BhDto.QuestNewWordRq, responseObserver: StreamObserver<BhDto.IdResponse>) {
        val id = questService.questNewWord(request.userId, request.collectionId)
        with(responseObserver) {
            onNext(BhDto.IdResponse.newBuilder()
                .setId(id)
                .build())
            onCompleted()
        }
    }

    override fun saveQuestResult(request: BhDto.SaveQuestResultRq, responseObserver: StreamObserver<Empty>) {
        questService.saveResult(request.userId, request.wordId, request.success)
        with(responseObserver) {
            onNext(Empty.getDefaultInstance())
            onCompleted()
        }
    }
}