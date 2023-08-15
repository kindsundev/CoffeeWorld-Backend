package com.example.util

import com.example.controller.*
import com.example.data.db.DatabaseConnector
import com.example.plugins.*
import com.example.repository.*
import io.ktor.server.application.*
import io.ktor.server.config.*

private val database = DatabaseConnector.connect
fun Application.initializedCafeRouting() {
    val repository = CafeRepository(database)
    val controller = CafeController(repository)
    configureCafeRouting(controller)
}

fun Application.initializedReviewsRouting() {
    val repository = ReviewsRepository(database)
    val controller = ReviewsController(repository)
    configureReviewsRouting(controller)
}

fun Application.initializedFavoriteRouting() {
    val repository = FavoriteRepository(database)
    val controller = FavoriteController(repository)
    configureFavoriteRouting(controller)
}

fun Application.initializedPaymentMethodRouting() {
    val repository = PaymentMethodRepository(database)
    val controller = PaymentMethodController(repository)
    configurePaymentMethodRouting(controller)
}

fun Application.initializedCartRouting() {
    val repository = CartRepository(database)
    val controller = CartController(repository)
    configureCartRouting(controller)
}

fun Application.initializedBillRouting() {
    val repository = BillRepository(database)
    val controller = BillController(repository)
    configureBillRouting(controller)
}

fun Application.initializedAuthRouting(config: HoconApplicationConfig) {
    val login = LoginRepository(database)
    val register = RegisterRepository(database)
    val password = PasswordRepository(database)
    val repository = AuthRepository(login, register, password, config)
    val controller = AuthController(repository)
    configureAuthRouting(controller, config)
}

fun Application.initializedUserRouting() {
    val repository = UserRepository(database)
    val controller = UserController(repository)
    configureUserRouting(controller)
}
