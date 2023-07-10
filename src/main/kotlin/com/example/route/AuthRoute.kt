package com.example.route

import com.example.controller.AuthController
import com.example.data.dto.LoginDTO
import com.example.data.dto.RegisterDTO
import com.example.response.ApiResponse
import com.example.common.Constants
import com.example.data.dto.AuthDTO
import com.example.util.TokenManagerUtil
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("AuthRoutes") }

fun Application.configureAuthRoutes(controller: AuthController, config: HoconApplicationConfig) {
    routing {
        route("/auth") {

            post("/register") {
                val request = call.receive<RegisterDTO>()
                try {
                    when (controller.registerUser(request)) {
                        Constants.INVALID_USER_DATA -> {
                            call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Invalid user data"))
                        }

                        Constants.USERNAME_EXIST -> {
                            call.respond(HttpStatusCode.Conflict, ApiResponse.Error("Username already exist"))
                        }

                        Constants.EMAIL_EXIST -> {
                            call.respond(HttpStatusCode.Conflict, ApiResponse.Error("Email already exist"))
                        }

                        Constants.REGISTER_FAILED -> {
                            call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Register account failed"))
                        }

                        else -> {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success("Register account successfully"))
                        }
                    }
                } catch (e: Exception) {
                    logger.error("Error at register user", e)
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

            post("/login") {
                val request = call.receive<LoginDTO>()
                try {
                    when (controller.loginUser(request)) {
                        true -> {
                            val token = TokenManagerUtil.getInstance(config).generateJWTToken(request.username)
                            call.respond(HttpStatusCode.OK, ApiResponse.Success(token))
                        }

                        false -> {
                            call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Password is incorrect"))
                        }

                        else -> {
                            call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Invalid user data"))
                        }
                    }
                } catch (e: Exception) {
                    logger.error("Error at login user", e)
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        ApiResponse.Error("An error occurred, please try again later")
                    )
                }
            }

            authenticate {
                post("/forget-password") {
                    val request = call.receive<AuthDTO>()
                    try {
                        when (controller.forgotPassword(request)) {
                            Constants.INVALID_USER_DATA -> {
                                call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Invalid user data"))
                            }
                            Constants.SQL_ERROR -> {
                                call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Update password error"))
                            }
                            Constants.SEND_EMAIL_FAILED -> {
                                call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Failed to send email"))
                            }
                            else -> {
                                call.respond(
                                    HttpStatusCode.OK,
                                    ApiResponse.Success("Password reset successful. Check your email for the new password")
                                )
                            }
                        }
                    } catch (e: Exception) {
                        logger.error("Error at reset password", e)
                        call.respond(
                            HttpStatusCode.InternalServerError, ApiResponse.Error("An error occurred, please try again later"))
                    }
                }
            }
        }
    }
}

