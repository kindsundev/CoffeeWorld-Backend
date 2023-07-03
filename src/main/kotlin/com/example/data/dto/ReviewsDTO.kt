package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReviewsDTO(
    val rating: Float,
    val comment: String? = null
)
