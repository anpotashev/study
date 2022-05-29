package ru.net.arh.study.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import ru.net.arh.study.security.data.TelegramAuthentication
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TelegramAuthentificationFilter(matcher: RequestMatcher, authenticationManager: TelegramAuthenticationManager) :
    AbstractAuthenticationProcessingFilter(matcher, authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val code = request.getHeader("code")
        val singleTimeAuthCode = request.getHeader("single_time_code")
        return authenticationManager.authenticate(TelegramAuthentication(code, singleTimeAuthCode))
    }
}