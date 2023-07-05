package com.example.plugins

import com.example.controller.*
import com.example.route.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Welcome to Coffee World!")
        }
    }
}

fun Application.configureCafeRouting(controller: CafeController) {
    configureCafeRoutes(controller)
}

fun Application.configureDrinksRouting(controller: DrinksController) {
    configureDrinksRoute(controller)
}

fun Application.configureCategoryRouting(controller: CategoryController) {
    configureCategoryRoutes(controller)
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