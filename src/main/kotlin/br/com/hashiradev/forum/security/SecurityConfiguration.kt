package br.com.hashiradev.forum.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    val jwtUtils: JWTUtils,
    val authenticationConfiguration: AuthenticationConfiguration
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            authorizeHttpRequests {
                authorize(HttpMethod.POST,"/login", permitAll)
                authorize(HttpMethod.PUT, "/**", hasAnyAuthority("WRITE"))
                authorize(anyRequest, authenticated)
            }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
        }
        return http
            .addFilterBefore(JWTValidateFilter(jwtUtils, authenticationConfiguration.authenticationManager), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(JWTAuthenticationFilter(jwtUtils, authenticationConfiguration.authenticationManager), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

}