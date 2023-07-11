package com.example.route

import com.example.controller.CategoryController
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("com.example.route.CategoryRouteKt") }

fun Application.configureCategoryRoutes(controller: CategoryController) {
    routing {
        route("/categories") {

            get {
                try {
                    val list = controller.getListCategories()
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found category list"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get categories", e)
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
                    val cafe = controller.getCategory(id)
                    if (cafe != null) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(cafe))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found cafe"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get category by id", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }
        }
    }
}
