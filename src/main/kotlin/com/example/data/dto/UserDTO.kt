package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val username: String,
    val password: String,
    val image: String? = null,
    val email: String,
    val name: String,
    val address: String? = null,
    val phone: String? = null
)