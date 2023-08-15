package com.example.plugins

import com.example.controller.*
import com.example.route.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.config.*

fun Application.configureWelcomeRouting() {
    routing {
        get("/") {
            call.respondText("Welcome to Coffee World!")
        }
    }
}

fun Application.configureCafeRouting(controller: CafeController) {
    configureCafeRoutes(controller)
}

fun Application.configureReviewsRouting(controller: ReviewsController) {
    configureReviewsRoutes(controller)
}

fun Application.configureFavoriteRouting(controller: FavoriteController) {
    configureFavoriteRoutes(controller)
}

fun Application.configurePaymentMethodRouting(controller: PaymentMethodController) {
    configurePaymentMethodRoutes(controller)
}

fun Application.configureCartRouting(controller: CartController) {
    configureCartRoutes(controller)
}

fun Application.configureBillRouting(controller: BillController) {
    configureBillRoutes(controller)
}

fun Application.configureAuthRouting(controller: AuthController,  config: HoconApplicationConfig) {
    configureAuthRoutes(controller, config)
}

fun Application.configureUserRouting(controller: UserController) {
    configureUserRoutes(controller)
}