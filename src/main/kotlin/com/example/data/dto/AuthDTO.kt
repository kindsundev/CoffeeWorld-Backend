package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthDTO(
    val username: String,
    val email: String
)