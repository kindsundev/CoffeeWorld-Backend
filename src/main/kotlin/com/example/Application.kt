package com.example

import com.example.controller.CafeController
import com.example.data.db.DatabaseConnector
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.repository.CafeRepository

private val database = DatabaseConnector.connect

fun main() {
    embeddedServer(Netty, port = 9999, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    initializedCafeRouting()
}

private fun Application.initializedCafeRouting() {
    val cafeRepository = CafeRepository(database)
    val cafeController = CafeController(cafeRepository)
    configureCafeRouting(cafeController)
}
