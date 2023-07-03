package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PaymentMethod(
    val id: Int,
    val name: String
)
