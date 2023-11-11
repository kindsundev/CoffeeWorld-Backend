package com.example.route

import com.example.controller.ReviewController
import com.example.data.dto.ReviewDTO
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("com.example.route.ReviewRouteKt") }

fun Application.configureReviewRoutes(controller: ReviewController) {
    routing {
        route("/review") {

            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val review = controller.getReview(id)
                    if (review != null) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(review))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found drinks"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get review by id", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later"))
                }
            }

            get("/drinks/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val list = controller.getReviewByDrinkId(id)
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found review"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get review by drink_id", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later"))
                }
            }

            post {
                val reviewRequest = call.receive<ReviewDTO>()
                try {
                    controller.createReview(reviewRequest)
                    call.respond(HttpStatusCode.Created, ApiResponse.Success("Create review is success"))
                } catch (e: Exception) {
                    logger.error("Error at create review", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

            put("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(
                        HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null")
                    )
                    return@put
                }

                val reviewRequest = call.receive<ReviewDTO>()
                try {
                    val updated = controller.updateReview(id, reviewRequest)
                    if (updated) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success("Updated to review"))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found review"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at update review", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later"))
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@delete
                }

                try {
                    val deleted = controller.deleteReview(id)
                    if (deleted) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success("Deleted to review"))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found review"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at delete review", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later"))
                }
            }
        }
    }
}