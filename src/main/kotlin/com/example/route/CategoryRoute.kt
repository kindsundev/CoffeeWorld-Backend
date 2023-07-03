package com.example.route

import com.example.controller.CategoryController
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("CategoryRoutes") }

fun Application.configureCategoryRoutes(categoryController: CategoryController) {
    routing {
        route("/categories") {

            get {
                try {
                    val list = categoryController.getListCategories()
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(true, list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error(false, "Not found list categories"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get list categories", e)
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
                    val cafe = categoryController.getCategory(id)
                    if (cafe != null) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(true, cafe))
                    } else {
                        call.respond(
                            HttpStatusCode.NotFound, ApiResponse.Error(false, "Not found cafe")
                        )
                    }
                } catch (e: Exception) {
                    logger.error("Error at get category by id", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error(false, "Something error")
                    )
                }
            }
        }
    }
}
