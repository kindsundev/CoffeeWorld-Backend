package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.util.*
import com.example.util.initializedCategoryRouting
import com.example.util.initializedDrinksRouting

fun main() {
    embeddedServer(Netty, port = 9999, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    initializedCafeRouting()
    initializedDrinksRouting()
    initializedCategoryRouting()
    initializedReviewsRouting()
    initializedFavoriteRouting()
    initializedPaymentMethodRouting()
    initializedCartRouting()
    initializedBillRouting()
}