package com.example.plugins

import com.example.controller.CafeController
import com.example.controller.DrinksController
import com.example.route.configureCafeRoutes
import com.example.route.configureDrinksRoute
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