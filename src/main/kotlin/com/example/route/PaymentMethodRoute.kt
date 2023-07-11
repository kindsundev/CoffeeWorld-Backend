package com.example.route

import com.example.controller.PaymentMethodController
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("com.example.route.PaymentMethodRouteKt") }

fun Application.configurePaymentMethodRoutes(controller: PaymentMethodController) {
    routing {
        route("/payment-methods") {
            get {
                try {
                    val list = controller.getListPaymentMethods()
                    if (list.isNotEmpty()) {
                        call.respond(HttpStatusCode.OK, ApiResponse.Success(list))
                    } else {
                        call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found list payment method"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at get payment method", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }
        }
    }
}
