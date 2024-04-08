package br.com.hashiradev.forum.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.Password
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


@Component
class JWTUtils {

    private val expirationTime =  (1000 * 60).toLong()

    @Value("\${jwt.secret}")
    lateinit var secretString: String
    fun generateJWT(userName: String): String {
        val key = getKey()
        return Jwts.builder()
            .subject(userName)
            .expiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(key)
            .compact()
    }

    fun isValid(jwtString: String): Boolean {
        return try {
            getparsedJwt(jwtString, getKey())
            true
        } catch (ex: IllegalArgumentException) {
            false
        }
    }

    fun getAuthenticationDetails(jwtString: String): Authentication {
        val jwt = getparsedJwt(jwtString, getKey())
        val username = jwt?.payload?.subject

        return UsernamePasswordAuthenticationToken(username, null, null)
    }

    private fun getparsedJwt(jwtString: String, key: SecretKey): Jws<Claims>? {
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