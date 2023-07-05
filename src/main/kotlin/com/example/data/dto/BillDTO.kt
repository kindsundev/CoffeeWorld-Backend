package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BillDTO(
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
