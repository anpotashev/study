package ru.net.arh.study.service

import com.google.protobuf.Empty
import com.google.protobuf.NullValue
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import ru.net.arh.study.bh.dto.BhDto
import ru.net.arh.study.bh.service.ExampleServiceGrpc
import ru.net.arh.study.data.Example

@GrpcService
class GrpcExampleService(
    private val exampleService: ExampleService
): ExampleServiceGrpc.ExampleServiceImplBase() {

    override fun create(request: BhDto.CreateExampleRq, responseObserver: StreamObserver<BhDto.IdResponse>) {
        val id = exampleService.create(request.userId, request.wordId, request.exampleText)
        with(responseObserver) {
            onNext(BhDto.IdResponse.newBuilder().setId(id).build())
            onCompleted()
        }
    }

    override fun getAll(request: BhDto.GetAllExamplesRq, responseObserver: StreamObserver<BhDto.Example>) {
        with(responseObserver) {
            exampleService.getAll(request.userId, request.wordId)
                .forEach { onNext(toDtoExample(it)) }
            onCompleted()
        }
    }

    override fun getById(request: BhDto.GetExampleRq, responseObserver: StreamObserver<BhDto.NullableExample>) {
        val example = exampleService.get(request.userId, request.exampleId)
        with(responseObserver) {
            onNext(toNullableExample(example))
            onCompleted()
        }
    }

    override fun edit(request: BhDto.EditExampleRq, responseObserver: StreamObserver<Empty>) {
        exampleService.update(request.userId, request.exampleId, request.exampleText)
        empty(responseObserver)
    }

    override fun delete(request: BhDto.DeleteExampleRq, responseObserver: StreamObserver<Empty>) {
        exampleService.delete(request.userId, request.exampleId)
        empty(responseObserver)
    }

    private fun empty(responseObserver: StreamObserver<Empty>) {
        with(responseObserver) {
            onNext(Empty.getDefaultInstance())
            onCompleted()
        }
    }

    private fun toNullableExample(example: Example?): BhDto.NullableExample =
        example?.let {
            BhDto.NullableExample.newBuilder()
                .setExample(
                    toDtoExample(example)
                )
                .build() } ?: BhDto.NullableExample.newBuilder()
            .setNull(NullValue.NULL_VALUE)
            .build()



    private fun toDtoExample(example: Example) =
        BhDto.Example.newBuilder()
            .setId(example.id)
            .setExampleText(example.text)
            .build()

}