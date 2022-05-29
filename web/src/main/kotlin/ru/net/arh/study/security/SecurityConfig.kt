package ru.net.arh.study.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val telegramAuthenticationManager: TelegramAuthenticationManager
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
//            .csrf().disable()
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET.name, "/auth")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterAt(telegramAuthFilter(),
                UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun telegramAuthFilter() =
        TelegramAuthentificationFilter(
            AntPathRequestMatcher("/auth", HttpMethod.POST.name),
            telegramAuthenticationManager
        )
            .apply {
                setAuthenticationSuccessHandler(SimpleUrlAuthenticationSuccessHandler("/auth/success"))
            }

}