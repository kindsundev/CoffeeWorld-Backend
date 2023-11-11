package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewModel(
    val id: Int,
    @SerialName("drink_id")
    val drinkId: Int,
    @SerialName("user_id")
    val userId: Int,
    val rating: Float,
    val comment: String? = null,
    val userName: String? = null
)
