package com.example.route

import com.example.controller.AuthenticationController
import com.example.data.dto.UserCredentialsDTO
import com.example.data.dto.UserDTO
import com.example.response.ApiResponse
import com.example.util.TokenManagerUtil
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

private val logger by lazy { LoggerFactory.getLogger("AuthRoutes") }

fun Application.configureAuthenticationRoutes(controller: AuthenticationController, config: HoconApplicationConfig) {
    routing {
        route("/register") {
            post {
                val userRequest = call.receive<UserDTO>()
                try {
                    when (controller.register(userRequest)) {
                        true -> {
                            call.respond(HttpStatusCode.OK, ApiResponse.Success("Register account success"))
                        }
                        false -> {
                            call.respond(
                                HttpStatusCode.BadRequest,
                                ApiResponse.Error("Username ${userRequest.username} already exists")
                            )
                        }
                        else -> call.respond(HttpStatusCode.BadRequest, ApiResponse.Error("Invalid user data"))
                    }
                } catch (e: Exception) {
                    logger.error("Error at register user", e)
                    call.respond(
                        HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to register user")
                    )
                }
            }
        }

        route("/login") {
            post {
                val auth = call.receive<UserCredentialsDTO>()
                try {
                    when(controller.login(auth.username, auth.password)) {
                        true -> {
                            val token = TokenManagerUtil.getInstance(config).generateJWTToken(auth.username)
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
                        HttpStatusCode.InternalServerError, ApiResponse.Error("Failed to login user")
                    )
                }
            }
        }
    }
}
