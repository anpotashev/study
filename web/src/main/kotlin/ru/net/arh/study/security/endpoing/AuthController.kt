package ru.net.arh.study.security.endpoing

import com.google.protobuf.Empty
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.net.arh.study.security.data.TelegramCode
import ru.net.arh.study.tg.service.TelegramAuthServiceGrpc

@RestController
@RequestMapping("/auth")
class AuthController {

    @GrpcClient("study-tg")
    private lateinit var telegramAuthServiceBlockingStub: TelegramAuthServiceGrpc.TelegramAuthServiceBlockingStub

    @GetMapping
    fun getCode() =
        telegramAuthServiceBlockingStub.startAuth(Empty.getDefaultInstance())
            .let{ TelegramCode(it.code, it.url) }

    @GetMapping("/success")
    fun successLogin() = "ok"
}