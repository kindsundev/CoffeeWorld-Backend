package com.example.route

import com.example.controller.FavoriteController
import com.example.data.dto.FavoriteDTO
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("com.example.route.FavoriteRouteKt") }

fun Application.configureFavoriteRoutes(controller: FavoriteController) {
    routing {
        route("/favorites") {

            get("/{user_id}") {
                val userId = call.parameters["user_id"]?.toIntOrNull()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val list = controller.getListFavoriteDrinks(userId)
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found list drinks"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get favorite drinks by user_id", e)
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

            authenticate {
                post {
                    val favoriteRequest = call.receive<FavoriteDTO>()
                    try {
                        controller.createFavorite(favoriteRequest)
                        call.respond(HttpStatusCode.Created, ApiResponse.Success("Added drink to favorite"))
                    } catch (e: Exception) {
                        logger.error("Error at create favorite", e)
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            ApiResponse.Error("An error occurred, please try again later")
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
                        val deleted = controller.deleteFavorite(id)
                        if (deleted) {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success("Deleted drink from favorite"))
                        } else {
                            call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found drink in favorite"))
                        }
                    } catch (e: Exception) {
                        logger.error("Error at delete favorite", e)
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            ApiResponse.Error("An error occurred, please try again later")
                        )
                    }
                }
            }
        }
    }
}