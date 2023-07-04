package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteDTO(
    @SerialName("user_id")
    val userId: Int,
    @SerialName("drinks_id")
    val drinksId: Int
)
