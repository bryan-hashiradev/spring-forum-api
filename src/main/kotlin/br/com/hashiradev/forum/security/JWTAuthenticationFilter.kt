package br.com.hashiradev.forum.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationFilter(
    private val jwtUtils: JWTUtils,
    authenticationManager: AuthenticationManager,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader: String? = request.getHeader("Authorization")
        if (authHeader != null) {
            val jwt = getToken(authHeader)

            if (jwtUtils.isValid(jwt)){
                val authentication = jwtUtils.getAuthenticationDetails(jwt)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun getToken(authHeader: String): String {
        return if (authHeader.startsWith("Bearer ")) {
            authHeader.split(" ")[1]
        } else {
            authHeader
        }
    }
}
