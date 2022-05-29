package ru.net.arh.study.security

import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.net.arh.study.tg.dto.TgDto
import ru.net.arh.study.security.data.TelegramAuthentication
import ru.net.arh.study.security.data.TelegramUserDetails
import ru.net.arh.study.tg.service.TelegramAuthServiceGrpc

@Service
class TelegramAuthenticationManager : AuthenticationManager {

    @GrpcClient("study-tg")
    private lateinit var telegramAuthServiceBlockingStub: TelegramAuthServiceGrpc.TelegramAuthServiceBlockingStub

    override fun authenticate(authentication: Authentication): Authentication {

        if (authentication is TelegramAuthentication) {
            val response = telegramAuthServiceBlockingStub.finishAuth(
                TgDto.TelegramFinishAuthRq.newBuilder()
                    .setCode(authentication.credentials.first)
                    .setSingleTimeCode(authentication.singleTimeCode)
                    .build()
            )
            if (response.dataCase == TgDto.TelegramFinishAuthRs.DataCase.NULL) {
                throw BadCredentialsException("Telegram chat not found for such code")
            }
            authentication.isAuthenticated = true
            authentication.details = TelegramUserDetails(response.chatId)
        }
        return authentication
    }
}