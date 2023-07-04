package com.example.route

import com.example.controller.CartController
import com.example.data.dto.CartDTO
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("CartRoutes") }

fun Application.configureCartRoutes(controller: CartController) {
    routing {
        route("/cart") {

            get("/user/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val list = controller.getCartByUserId(id)
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found cart"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get cart by user_id", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("Something error"))
                }
            }

            post {
                val reviewsRequest = call.receive<CartDTO>()
                try {
                    controller.createCart(reviewsRequest)
                    call.respond(HttpStatusCode.Created, ApiResponse.Success("Create cart is success"))
                } catch (e: Exception) {
                    logger.error("Error at create cart", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to create cart")
                    )
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@delete
                }

                try {
                    val deleted = controller.deleteCart(id)
                    if (deleted) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success("Deleted to cart"))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found cart"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at delete cart", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to delete cart"))
                }
            }
        }
    }
}
