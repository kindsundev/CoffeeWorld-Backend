package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class EmailDTO(
    val username: String,
    val email: String,
    val password: String
)