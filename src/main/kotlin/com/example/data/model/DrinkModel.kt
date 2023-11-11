package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrinkModel(
    val id: Int,
    @SerialName("cafe_id")
    val cafeId: Int,
    val name: String,
    val quantity: Int,
    val price: Double,
    val description: String,
    val image: String? = null,
    @SerialName("category_id")
    val categoryId: Int,
    val rating: Float? = null
)
