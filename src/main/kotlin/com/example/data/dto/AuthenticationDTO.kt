package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationDTO(
    val username: String,
    val password: String
)