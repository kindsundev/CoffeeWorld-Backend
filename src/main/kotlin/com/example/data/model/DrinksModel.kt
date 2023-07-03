package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DrinksModel(
    val id: Int,
    val cafeId: Int,
    val name: String,
    val quantity: Int,
    val price: Double,
    val description: String,
    val image: String? = null,
    val categoryId: Int,
    val rating: Float? = null
)
