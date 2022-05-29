package ru.net.arh.study.security.data

import org.springframework.security.authentication.AbstractAuthenticationToken
import java.util.*

class TelegramAuthentication(
    val code: String,
    val singleTimeCode: String
): AbstractAuthenticationToken(emptyList()) {

    override fun getCredentials(): Pair<String, String> {
        return code to singleTimeCode
    }

    override fun getPrincipal(): Any {
        return ""
    }


}