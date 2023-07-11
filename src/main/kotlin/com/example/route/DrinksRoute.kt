package com.example.route

import com.example.controller.DrinksController
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("com.example.route.DrinksRouteKt") }

fun Application.configureDrinksRoute(controller: DrinksController) {
    routing {
        route("/drinks") {

            get {
                try {
                    val list = controller.getListDrinks()
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found list drinks"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get list drinks", e)
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
                    val cafe = controller.getDrinks(id)
                    if (cafe != null) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(cafe))
                    } else {
                        call.respond(
                            HttpStatusCode.NotFound, ApiResponse.Error("Not found drinks")
                        )
                    }
                } catch (e: Exception) {
                    logger.error("Error at get drinks by id", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

            put("/{id}/quantity") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@put
                }

                try {
                    val quantity = call.receiveText().toIntOrNull()
                    if (quantity == null) {
                        call.respond(
                            HttpStatusCode.BadRequest, ApiResponse.Error("Quantity is numeric or not null")
                        )
                    } else {
                        val updated = controller.updateQuantityDrinks(id, quantity)
                        if (updated) {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success("Updated to quantity"))
                        } else {
                            call.respond(
                                HttpStatusCode.NotFound, ApiResponse.Error("Not found drinks")
                            )
                        }
                    }
                } catch (e: Exception) {
                    logger.error("Error at update quantity", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

        }
    }
}
