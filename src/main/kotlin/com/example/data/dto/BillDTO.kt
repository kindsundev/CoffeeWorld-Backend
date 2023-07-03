package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BillDTO(
    val paymentMethodId: Int,
    val date: String,
    val amount: Double,
    val completed: String
)
