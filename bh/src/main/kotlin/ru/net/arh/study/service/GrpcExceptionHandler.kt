package ru.net.arh.study.service

import io.grpc.Status
import net.devh.boot.grpc.server.advice.GrpcAdvice
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler
import ru.net.arh.study.exception.BhRowNotFoundException

@GrpcAdvice
class GrpcExceptionHandler {
    @GrpcExceptionHandler
    fun onBhRowNotFoundException(exception: BhRowNotFoundException): Status =
        Status.NOT_FOUND
            .withDescription("Row not found")
            .withCause(exception)
}