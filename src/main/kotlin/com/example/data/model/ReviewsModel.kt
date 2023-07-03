package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewsModel(
    val id: Int,
    val drinksId: Int,
    val userId: Int,
    val rating: Float,
    val comment: String? = null
)
