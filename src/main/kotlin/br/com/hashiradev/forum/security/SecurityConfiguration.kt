package br.com.hashiradev.forum.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize("/topic", hasAnyAuthority("READ_WRITE"))
                authorize("/topic/not-answered", hasAnyAuthority("READ_WRITE"))
                authorize(anyRequest, authenticated)
            }
            httpBasic {}
        }
        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}