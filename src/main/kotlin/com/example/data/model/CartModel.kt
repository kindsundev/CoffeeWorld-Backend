package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CartModel(
    val id: Int,
    val userId: Int,
    val drinksId: Int,
    val quantity: Int
)
