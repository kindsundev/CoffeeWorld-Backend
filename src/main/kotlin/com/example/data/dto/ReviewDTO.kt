package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDTO(
    @SerialName("drink_id")
    val drinkId: Int,
    @SerialName("user_id")
    val userId: Int,
    val rating: Float,
    val comment: String? = null
)
