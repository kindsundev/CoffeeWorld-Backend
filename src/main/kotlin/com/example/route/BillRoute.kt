package com.example.route

import com.example.controller.BillController
import com.example.data.dto.BillDTO
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("BillRoutes") }

fun Application.configureBillRoutes(controller: BillController) {
    routing {
        route("/bill") {
            get("/{user_id}") {
                val id = call.parameters["user_id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Id cannot be null"))
                    return@get
                }
                try {
                    val bill = controller.getBillByUserId(id)
                    if (bill.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(bill))
                    } else {
                        call.respond(
                            HttpStatusCode.NotFound, ApiResponse.Error("Not found bill")
                        )
                    }
                } catch (e: Exception) {
                    logger.error("Error at get bill by user_id", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("Something error")
                    )
                }
            }

            post {
                val billRequest = call.receive<BillDTO>()
                try {
                    val bill = controller.createBill(billRequest)
                    if (bill != null) {
                        call.respond(HttpStatusCode.Created, ApiResponse.Success(bill))
                    } else {
                        call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Please check cart before create bill"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at create bill", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to create bill")
                    )
                }
            }
        }
    }
}