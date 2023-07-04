package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemModel(
    val id: Int,
    @SerialName("cart_id")
    val cartId: Int,
    @SerialName("drinks_id")
    val drinksId: Int,
    val quantity: Int
)