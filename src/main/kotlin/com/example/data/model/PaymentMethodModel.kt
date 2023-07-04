package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PaymentMethodModel(
    val id: Int,
    val name: String
)
