package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val id: Int,
    @SerialName("cafe_id")
    val cafeId: Int,
    val name: String
)
