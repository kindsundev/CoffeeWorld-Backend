package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDTO(
    val username: String,
    val password: String,
    val email: String,
    val name: String
)