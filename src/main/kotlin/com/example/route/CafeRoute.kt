package com.example.route

import com.example.controller.CafeController
import com.example.response.ApiResponse
import io.ktor.http.*
import org.slf4j.LoggerFactory
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val logger by lazy { LoggerFactory.getLogger("com.example.route.CafeRouteKt") }

fun Application.configureCafeRoutes(controller: CafeController) {
    routing {
        route("/cafes") {

            get {
                try {
                    val list = controller.getCafeList()
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("The list of cafes could not be found"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get cafe list", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

            get("/{cafe_id}/categories") {
                val cafeId = call.parameters["cafe_id"]?.toIntOrNull()
                if (cafeId == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val list = controller.getCategoryList(cafeId)
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(
                            HttpStatusCode.NotFound, ApiResponse.Error("The list of categories could not be found")
                        )
                    }
                } catch (e: Exception) {
                    logger.error("Error at get category list", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

            get("/{cafe_id}/drinks") {
                val cafeId = call.parameters["cafe_id"]?.toIntOrNull()
                if (cafeId == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val list = controller.getDrinkList(cafeId)
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("No data found"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get category list", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

            get("/{cafe_id}/category/{category_id}/drinks") {
                val cafeId = call.parameters["cafe_id"]?.toIntOrNull()
                val categoryId = call.parameters["category_id"]?.toIntOrNull()
                if (cafeId == null || categoryId == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val drinkList = controller.getDrinkListByCategory(cafeId, categoryId)
                    if (drinkList.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(drinkList))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("No data found"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get drinks", e)
                    call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later"))
                }
            }

            get("/{cafe_id}/menu") {
                val cafeId = call.parameters["cafe_id"]?.toIntOrNull()
                if (cafeId == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val menu = controller.getMenu(cafeId)
                    if (menu.beverageCategory.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(menu))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("No data found"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get menu", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }
        }

        route("/drink/") {

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
                        val updated = controller.updateQuantityDrink(id, quantity)
                        if (updated) {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success("Updated to quantity"))
                        } else {
                            call.respond(
                                HttpStatusCode.NotFound, ApiResponse.Error("Not found drink")
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