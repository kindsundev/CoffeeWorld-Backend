package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemDTO(
    @SerialName("cart_id")
    val cartId: Int,
    @SerialName("drink_id")
    val drinkId: Int,
    val quantity: Int
)