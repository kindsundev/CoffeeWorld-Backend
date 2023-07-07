package com.example.plugins

import com.example.util.TokenManagerUtil
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*

fun Application.configureAuthentication(config: HoconApplicationConfig) {
    install(Authentication) {
        jwt {
            realm = config.property("realm").getString()
            verifier(TokenManagerUtil.getInstance(config).verifyJWTToken())
            validate { credential ->
                if (credential.payload.getClaim("username").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }
}
