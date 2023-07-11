package com.example.route

import com.example.common.Constants
import com.example.controller.UserController
import com.example.data.dto.EmailDTO
import com.example.data.dto.PasswordDTO
import com.example.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("com.example.route.UserRouteKt") }

fun Application.configureUserRoutes(controller: UserController) {
    routing {
        authenticate {
            route("/user") {
                get("{username}") {
                    val request = call.parameters["username"]
                    if (request == null) {
                        call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Username cannot be null"))
                        return@get
                    }
                    try {
                        val user = controller.getUser(request)
                        if (user != null) {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success(user))
                        } else {
                            call.respond(HttpStatusCode.NotFound, ApiResponse.Error("Not found user"))
                        }
                    } catch (e: Exception) {
                        logger.error("Error at get user by username", e)
                        call.respond(
                            HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                        )
                    }
                }

                put("/name/{username}") {
                    val username = call.parameters["username"].toString()
                    val name = call.receiveText()
                    if (username.isEmpty() || name.isEmpty()) {
                        val message = if (username.isEmpty()) "Username cannot be null" else "Name cannot be null or empty"
                        call.respond(HttpStatusCode.BadRequest, ApiResponse.Error(message))
                        return@put
                    }

                    try {
                        val result = controller.updateName(username, name)
                        if (result) {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success("Update name success"))
                        } else {
                            call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Update name failed"))
                        }
                    } catch (e: Exception) {
                        logger.error("Error at update name", e)
                        call.respond(
                            HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                        )
                    }
                }

                put("/phone/{username}") {
                    val username = call.parameters["username"].toString()
                    val phone = call.receiveText()
                    if (username.isEmpty() || phone.isEmpty()) {
                        val message = if (username.isEmpty()) "Username cannot be null" else "Phone cannot be null or empty"
                        call.respond(HttpStatusCode.BadRequest, ApiResponse.Error(message))
                        return@put
                    }

                    try {
                        val result = controller.updatePhone(username, phone)
                        if (result) {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success("Update phone success"))
                        } else {
                            call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Update phone failed"))
                        }
                    } catch (e: Exception) {
                        logger.error("Error at update phone", e)
                        call.respond(
                            HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                        )
                    }
                }

                put("/address/{username}") {
                    val username = call.parameters["username"].toString()
                    val address = call.receiveText()
                    if (username.isEmpty() || address.isEmpty()) {
                        val message = if (username.isEmpty()) "Username cannot be null" else "Address cannot be null or empty"
                        call.respond(HttpStatusCode.BadRequest, ApiResponse.Error(message))
                        return@put
                    }

                    try {
                        val result = controller.updateAddress(username, address)
                        if (result) {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success("Update address success"))
                        } else {
                            call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Update address failed"))
                        }
                    } catch (e: Exception) {
                        logger.error("Error at update address", e)
                        call.respond(
                            HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                        )
                    }
                }

                put("/avatar/{username}") {
                    val username = call.parameters["username"].toString()
                    val base64 = call.receiveText()
                    if (username.isEmpty() || base64.isEmpty()) {
                        val message = if (username.isEmpty()) "Username cannot be null" else "Base64 cannot be null or empty"
                        call.respond(HttpStatusCode.BadRequest, ApiResponse.Error(message))
                        return@put
                    }

                    try {
                        val result = controller.updateAvatar(username, base64)
                        if (result) {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success("Update avatar success"))
                        } else {
                            call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Update avatar failed"))
                        }
                    } catch (e: Exception) {
                        logger.error("Error at update avatar", e)
                        call.respond(
                            HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later")
                        )
                    }
                }

                put("/email") {
                    val request = call.receive<EmailDTO>()
                    if (request.username.isEmpty()) {
                        call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Username cannot be null"))
                        return@put
                    }

                    try {
                        when (controller.updateEmail(request)) {
                            Constants.AUTH_FAILED -> {
                                call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Not found user"))
                            }
                            Constants.INCORRECT_PASSWORD -> {
                                call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Incorrect password"))
                            }
                            Constants.EMAIL_EXIST -> {
                                call.respond(HttpStatusCode.Conflict, ApiResponse.Error("Email already exist"))
                            }
                            Constants.UPDATE_EMAIL_FAILED -> {
                                call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to update email"))
                            }
                            else -> {
                                call.respond(HttpStatusCode.OK, ApiResponse.Success("Update email success"))
                            }
                        }
                    } catch (e: Exception) {
                        logger.error("Error at update email", e)
                        call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later"))
                    }
                }

                put("/password") {
                    val request = call.receive<PasswordDTO>()
                    if (request.username.isEmpty()) {
                        call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Username cannot be null"))
                        return@put
                    }

                    try {
                        when (controller.updatePassword(request)) {
                            Constants.AUTH_FAILED -> {
                                call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Not found user"))
                            }
                            Constants.INCORRECT_PASSWORD -> {
                                call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Incorrect password"))
                            }
                            Constants.WARN_PASSWORD_LENGTH -> {
                                call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Require password length large 8"))
                            }
                            Constants.UPDATE_PASSWORD_FAILED -> {
                                call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to update password"))
                            }
                            else -> {
                                call.respond(HttpStatusCode.OK, ApiResponse.Success("Update password success"))
                            }
                        }
                    } catch (e: Exception) {
                        logger.error("Error at update password", e)
                        call.respond(HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later"))
                    }
                }
            }
        }

    }
}
