package ru.net.arh.study.service

import com.google.protobuf.Empty
import com.google.protobuf.NullValue
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import ru.net.arh.study.bh.dto.BhDto
import ru.net.arh.study.bh.service.WordCollectionServiceGrpc
import ru.net.arh.study.data.WordCollection

@GrpcService
class GrpcWordCollectionService(
    private val wordCollectionService: WordCollectionService
) : WordCollectionServiceGrpc.WordCollectionServiceImplBase() {
    override fun create(
        request: BhDto.CreateWordCollectionRq,
        responseObserver: StreamObserver<BhDto.IdResponse>
    ) {
        val id = wordCollectionService.create(request.userId, request.name)
        with(responseObserver) {
            onNext(BhDto.IdResponse.newBuilder().setId(id).build())
            onCompleted()
        }
    }

    override fun getAll(
        request: BhDto.GetAllCollectionRq,
        responseObserver: StreamObserver<BhDto.WordCollection>
    ) {
        with(responseObserver) {
            wordCollectionService.getWordCollections(request.userId)
                .forEach { onNext(toDtoWordCollection(it)) }
            onCompleted()
        }
    }

    override fun getById(
        request: BhDto.GetCollectionRq,
        responseObserver: StreamObserver<BhDto.NullableWordCollection>
    ) {
        val wordCollection = wordCollectionService.getWordCollection(request.userId, request.collectionId)
        with(responseObserver) {
            onNext(toNullableWordCollection(wordCollection))
            onCompleted()
        }
    }

    override fun edit(
        request: BhDto.RenameWordCollectionRq,
        responseObserver: StreamObserver<Empty>
    ) {
        wordCollectionService.edit(request.userId, request.wordCollectionId, request.newName)
        empty(responseObserver)
    }

    override fun delete(request: BhDto.DeleteWordCollectionRq, responseObserver: StreamObserver<Empty>) {
        wordCollectionService.delete(request.userId, request.wordCollectionId)
        empty(responseObserver)
    }

    private fun empty(responseObserver: StreamObserver<Empty>) {
        with(responseObserver) {
            onNext(Empty.getDefaultInstance())
            onCompleted()
        }
    }

    private fun toDtoWordCollection(wordCollection: WordCollection) =
        BhDto.WordCollection.newBuilder()
            .setId(wordCollection.id)
            .setName(wordCollection.name)
            .build()

    private fun toNullableWordCollection(wordCollection: WordCollection?): BhDto.NullableWordCollection =
        wordCollection?.let {
            BhDto.NullableWordCollection.newBuilder()
                .setWordCollection(
                    toDtoWordCollection(wordCollection)
                )
                .build() } ?: BhDto.NullableWordCollection.newBuilder()
                .setNull(NullValue.NULL_VALUE)
                .build()

}