package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DrinksDTO(
    val quantity: Int,
    val rating: Float? = null
)
