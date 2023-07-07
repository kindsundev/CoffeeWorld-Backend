package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.util.*
import com.example.util.initializedCategoryRouting
import com.example.util.initializedDrinksRouting
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

fun main() {
    embeddedServer(Netty, port = 9999, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val config by lazy { HoconApplicationConfig(ConfigFactory.load()) }
    configureRouting()
    configureSerialization()
    configureAuthentication(config)
    initializedCafeRouting()
    initializedCartRouting()
    initializedBillRouting()
    initializedDrinksRouting()
    initializedReviewsRouting()
    initializedFavoriteRouting()
    initializedCategoryRouting()
    initializedPaymentMethodRouting()
    initializedAuthenticationRouting(config)
}