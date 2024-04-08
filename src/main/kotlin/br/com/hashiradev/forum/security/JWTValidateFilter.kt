package br.com.hashiradev.forum.security

import br.com.hashiradev.forum.DTO.CredentialsDTO
import br.com.hashiradev.forum.adapter.UserDetailsImplAdapter
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTValidateFilter(
    private val jwtUtils: JWTUtils,
    private val authManager: AuthenticationManager,
): UsernamePasswordAuthenticationFilter() {


    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
    val credentials = ObjectMapper().readValue(request?.inputStream, CredentialsDTO::class.java)

        val user = credentials.let {
            UsernamePasswordAuthenticationToken(it.username, it.password)
        }
        return authManager.authenticate(user)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val username = (authResult?.principal as UserDetailsImplAdapter).username

        val token = jwtUtils.generateJWT(username)
        response?.addHeader("Authorization", "Bearer ${token}")
    }
}