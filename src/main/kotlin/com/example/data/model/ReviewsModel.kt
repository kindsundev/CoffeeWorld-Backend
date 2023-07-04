package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewsModel(
    val id: Int,
    @SerialName("drinks_id")
    val drinksId: Int,
    @SerialName("user_id")
    val userId: Int,
    val rating: Float,
    val comment: String? = null,
    val userName: String? = null
)
