package com.example.util

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.config.*
import java.util.*

class TokenManagerUtil private constructor(config: HoconApplicationConfig) {
    private val audience = config.property("ktor.jwt.audience").getString()
    private val issuer = config.property("ktor.jwt.issuer").getString()
    private val secret = config.property("ktor.jwt.secret").getString()
    private val expirationTime : Date = Calendar.getInstance().apply {
        add(Calendar.YEAR, 1)
    }.time

    companion object {
        private var instance: TokenManagerUtil? = null

        fun getInstance(config: HoconApplicationConfig): TokenManagerUtil {
            return instance ?: synchronized(this) {
                instance ?: TokenManagerUtil(config).also { instance = it }
            }
        }
    }

    fun generateJWTToken(username: String): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", username)
            .withExpiresAt(expirationTime)
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyJWTToken(): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }
}