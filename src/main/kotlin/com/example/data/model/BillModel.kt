package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BillModel(
    val id: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("cart_id")
    val cartId: Int,
    @SerialName("payment_method_id")
    val paymentMethodId: Int,
    val date: String,
    val amount: Double,
    val completed: String
)
