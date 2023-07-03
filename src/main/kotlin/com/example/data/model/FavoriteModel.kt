package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteModel(
    val id: Int,
    val userId: Int,
    val drinksId: Int
)
