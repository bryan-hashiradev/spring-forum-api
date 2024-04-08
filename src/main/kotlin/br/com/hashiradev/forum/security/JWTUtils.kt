package br.com.hashiradev.forum.security

import br.com.hashiradev.forum.service.UserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


@Component
class JWTUtils (private val userService: UserService) {

    private val expirationTime =  (1000 * 60 * 5).toLong() //miliseconds 1s * to n seconds * to n minutes

    @Value("\${jwt.secret}")
    lateinit var secretString: String
    fun generateJWT(userDetails: UserDetails): String {
        val key = getKey()
        return Jwts.builder()
            .subject(userDetails.username)
            .claim("roles", userDetails.authorities)
            .expiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(key)
            .compact()
    }

    fun isValid(jwtString: String): Boolean {
        return try {
            getParsedJwt(jwtString, getKey())
            true
        } catch (ex: IllegalArgumentException) {
            false
        }
    }

    fun getAuthenticationDetails(jwtString: String): Authentication {
        val jwt = getParsedJwt(jwtString, getKey())
        val username = jwt?.payload?.subject
        val userDetails = userService.loadUserByUsername(username)

        return UsernamePasswordAuthenticationToken(username, null, userDetails?.authorities)
    }

    private fun getParsedJwt(jwtString: String, key: SecretKey): Jws<Claims>? {
        return Jwts
            .parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(jwtString)
    }

    private fun getKey(): SecretKey {
        return  Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString))
    }
}