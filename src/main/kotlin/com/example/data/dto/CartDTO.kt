package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartDTO(
    @SerialName("user_id")
    val userId: Int,
    val name: String,
    val date: String
)