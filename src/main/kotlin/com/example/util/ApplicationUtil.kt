package com.example.util

import com.example.controller.CafeController
import com.example.controller.CategoryController
import com.example.controller.DrinksController
import com.example.controller.ReviewsController
import com.example.data.db.DatabaseConnector
import com.example.plugins.configureCafeRouting
import com.example.plugins.configureCategoryRouting
import com.example.plugins.configureDrinksRouting
import com.example.plugins.configureReviewsRouting
import com.example.repository.CafeRepository
import com.example.repository.CategoryRepository
import com.example.repository.DrinksRepository
import com.example.repository.ReviewsRepository
import io.ktor.server.application.*

private val database = DatabaseConnector.connect
fun Application.initializedCafeRouting() {
    val cafeRepository = CafeRepository(database)
    val cafeController = CafeController(cafeRepository)
    configureCafeRouting(cafeController)
}

fun Application.initializedDrinksRouting() {
    val drinksRepository = DrinksRepository(database)
    val drinksController = DrinksController(drinksRepository)
    configureDrinksRouting(drinksController)
}

fun Application.initializedCategoryRouting() {
    val categoryRepository = CategoryRepository(database)
    val categoryController = CategoryController(categoryRepository)
    configureCategoryRouting(categoryController)
}

fun Application.initializedReviewsRouting() {
    val reviewsRepository = ReviewsRepository(database)
    val reviewsController = ReviewsController(reviewsRepository)
    configureReviewsRouting(reviewsController)
}