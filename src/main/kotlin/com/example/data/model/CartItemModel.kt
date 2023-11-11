package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemModel(
    val id: Int,
    @SerialName("cart_id")
    val cartId: Int,
    @SerialName("drink_id")
    val drinkId: Int,
    val quantity: Int
)