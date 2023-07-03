package com.example.route

import com.example.controller.ReviewsController
import com.example.data.dto.ReviewsDTO
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("ReviewsRoutes") }

fun Application.configureReviewsRoutes(reviewsController: ReviewsController) {
    routing {
        route("/reviews") {

            get {
                try {
                    val list = reviewsController.getListReviews()
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found list reviews"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get list drinks", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("Something error"))
                }
            }

            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val reviews = reviewsController.getReviews(id)
                    if (reviews != null) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(reviews))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found drinks"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get reviews by id", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("Something error"))
                }
            }

            post {
                val reviewsRequest = call.receive<ReviewsDTO>()
                try {
                    reviewsController.createReviews(reviewsRequest)
                    call.respond(HttpStatusCode.Created, ApiResponse.Success("Create reviews is success"))
                } catch (e: Exception) {
                    logger.error("Error at create reviews", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to create reviews")
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

                val reviewsRequest = call.receive<ReviewsDTO>()
                try {
                    val updated = reviewsController.updateReviews(id, reviewsRequest)
                    if (updated) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success("Updated to reviews"))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found reviews"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at update reviews", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to update reviews"))
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@delete
                }

                try {
                    val deleted = reviewsController.deleteReviews(id)
                    if (deleted) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success("Deleted to reviews"))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found reviews"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at delete reviews", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to delete reviews"))
                }
            }
        }
    }
}