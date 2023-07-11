package com.example.route

import com.example.controller.CafeController
import com.example.response.ApiResponse
import io.ktor.http.*
import org.slf4j.LoggerFactory
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val logger by lazy { LoggerFactory.getLogger("com.example.route.CafeRouteKt") }

fun Application.configureCafeRoutes(controller: CafeController) {
    routing {
        route("/cafes") {

            get {
                try {
                    val list = controller.getListCafes()
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("The list of cafes could not be found"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get list cafes", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val cafe = controller.getCafe(id)
                    if (cafe != null) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(cafe))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found cafe"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get cafe by id", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }
        }
    }
}