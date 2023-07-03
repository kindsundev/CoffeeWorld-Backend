package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    val id: Int,
    val cafeId: Int,
    val name: String
)
