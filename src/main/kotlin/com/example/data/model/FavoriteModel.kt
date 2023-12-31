package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteModel(
    val id: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("drink_id")
    val drinkId: Int
)
