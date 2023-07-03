package com.example.route

import com.example.controller.DrinksController
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("DrinksRoutes") }

fun Application.configureDrinksRoute(drinksController: DrinksController) {
    routing {
        route("/drinks") {

            get {
                try {
                    val list = drinksController.getListDrinks()
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(true, list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error(false, "Not found list drinks"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get list drinks", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error(false, "Something error")
                    )
                }
            }

            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error(false, "Id cannot be null"))
                    return@get
                }
                try {
                    val cafe = drinksController.getDrinks(id)
                    if (cafe != null) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(true, cafe))
                    } else {
                        call.respond(
                            HttpStatusCode.NotFound, ApiResponse.Error(false, "Not found drinks")
                        )
                    }
                } catch (e: Exception) {
                    logger.error("Error at get drinks by id", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error(false, "Something error")
                    )
                }
            }

            put("/{id}/quantity") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error(false, "Id cannot be null"))
                    return@put
                }

                try {
                    val quantity = call.receiveText().toIntOrNull()
                    if (quantity == null) {
                        call.respond(
                            HttpStatusCode.BadRequest, ApiResponse.Error(false, "Quantity is numeric or not null")
                        )
                    } else {
                        val updated = drinksController.updateQuantityDrinks(id, quantity)
                        if (updated) {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success(true, "Updated to quantity"))
                        } else {
                            call.respond(
                                HttpStatusCode.NotFound, ApiResponse.Error(false, "Not found drinks")
                            )
                        }
                    }
                } catch (e: Exception) {
                    logger.error("Error at update quantity", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error(false, "Something error")
                    )
                }
            }

        }
    }
}
