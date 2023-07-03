package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewsDTO(
    @SerialName("drinks_id")
    val drinksId: Int,
    @SerialName("user_id")
    val userId: Int,
    val rating: Float,
    val comment: String? = null
)
