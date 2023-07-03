package com.example

import com.example.controller.CafeController
import com.example.controller.CategoryController
import com.example.controller.DrinksController
import com.example.data.db.DatabaseConnector
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.repository.CafeRepository
import com.example.repository.CategoryRepository
import com.example.repository.DrinksRepository

private val database = DatabaseConnector.connect

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
}

private fun Application.initializedCafeRouting() {
    val cafeRepository = CafeRepository(database)
    val cafeController = CafeController(cafeRepository)
    configureCafeRouting(cafeController)
}

private fun Application.initializedDrinksRouting() {
    val drinksRepository = DrinksRepository(database)
    val drinksController = DrinksController(drinksRepository)
    configureDrinksRouting(drinksController)
}

private fun Application.initializedCategoryRouting() {
    val categoryRepository = CategoryRepository(database)
    val categoryController = CategoryController(categoryRepository)
    configureCategoryRouting(categoryController)
}