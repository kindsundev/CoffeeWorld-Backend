package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartModel(
    val id: Int,
    @SerialName("user_id")
    val userId: Int,
    val name: String,
    val date: String,
    @SerialName("drinks_oder")
    val drinksOrder: List<CartItemModel>? = null
)
