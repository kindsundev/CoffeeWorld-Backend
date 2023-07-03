package com.example.plugins

import com.example.controller.CafeController
import com.example.controller.CategoryController
import com.example.controller.DrinksController
import com.example.controller.ReviewsController
import com.example.route.configureCafeRoutes
import com.example.route.configureCategoryRoutes
import com.example.route.configureDrinksRoute
import com.example.route.configureReviewsRoutes
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

fun Application.configureCafeRouting(cafeController: CafeController) {
    configureCafeRoutes(cafeController)
}

fun Application.configureDrinksRouting(drinksController: DrinksController) {
    configureDrinksRoute(drinksController)
}

fun Application.configureCategoryRouting(categoryController: CategoryController) {
    configureCategoryRoutes(categoryController)
}

fun Application.configureReviewsRouting(reviewsController: ReviewsController) {
    configureReviewsRoutes(reviewsController)
}