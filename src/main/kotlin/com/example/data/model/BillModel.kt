package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BillModel(
    val id: Int,
    val userId: Int,
    val cartId: Int,
    val paymentMethodId: Int,
    val date: String,
    val amount: Double,
    val completed: String
)
