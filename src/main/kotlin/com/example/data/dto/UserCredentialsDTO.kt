package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentialsDTO(
    val username: String,
    val password: String
)