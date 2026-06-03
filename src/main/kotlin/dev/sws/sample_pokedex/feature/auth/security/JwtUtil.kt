package dev.sws.sample_pokedex.feature.auth.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil(
    @Value("\${jwt.secret}") private val secretKey: String,
    @Value("\${jwt.expiration}") private val expiration: Long,
) {

    private val signingKey: SecretKey
        get() {
            val keyBytes = Decoders.BASE64.decode(secretKey)
            return Keys.hmacShaKeyFor(keyBytes)
        }

    fun generateToken(username: String): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration)

        return Jwts.builder()
            .subject(username)
            .issuedAt(now)
            .expiration(expiryDate)
            .signWith(signingKey)
            .compact()
    }

    fun extractUsername(token: String): String? {
        return extractAllClaims(token = token).subject
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            !isTokenExpired(token)
        } catch (e: Exception) {
            false
        }
    }

    fun isTokenExpired(token: String): Boolean {
        val expirationDate = extractAllClaims(token).expiration
        return expirationDate.before(Date())
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(signingKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }

}