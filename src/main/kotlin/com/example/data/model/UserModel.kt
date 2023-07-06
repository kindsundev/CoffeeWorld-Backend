package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val username: String,
    val image: String? = null,
    val email: String,
    val name: String,
    val address: String? = null,
    val phone: String? = null
)