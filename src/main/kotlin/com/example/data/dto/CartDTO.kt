package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartDTO(
    val drinksId: Int,
    val quantity: Int
)
