package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val image: String? = null,
    val name: String,
    val address: String? = null,
    val phone: String? = null
)