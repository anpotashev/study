package ru.net.arh.study.service

import com.google.protobuf.Empty
import com.google.protobuf.NullValue
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.beans.factory.annotation.Value
import ru.net.arh.study.tg.dto.TgDto
import ru.net.arh.study.tg.service.TelegramAuthServiceGrpc
import java.util.*

@GrpcService
class GrpcTelegramAuthServiceImpl(
    private val telegramAuthService: TelegramAuthService
) : TelegramAuthServiceGrpc.TelegramAuthServiceImplBase() {


    override fun startAuth(request: Empty, responseObserver: StreamObserver<TgDto.TelegramStartAuthRs>) {
        val startAuthData = telegramAuthService.startAuth()
        with(responseObserver) {
            onNext(
                TgDto.TelegramStartAuthRs.newBuilder()
                    .setCode(startAuthData.first)
                    .setUrl(startAuthData.second)
                    .build()
            )
            onCompleted()
        }
    }

    override fun finishAuth(
        request: TgDto.TelegramFinishAuthRq,
        responseObserver: StreamObserver<TgDto.TelegramFinishAuthRs>
    ) {
        val chatId = telegramAuthService.finishAuth(request.code, request.singleTimeCode)
        val response = chatId?.let {
            TgDto.TelegramFinishAuthRs.newBuilder()
                .setChatId(it)
                .build()
        } ?: TgDto.TelegramFinishAuthRs.newBuilder()
            .setNull(NullValue.NULL_VALUE)
            .build()
        with(responseObserver) {
            onNext(response)
            onCompleted()
        }
    }
}