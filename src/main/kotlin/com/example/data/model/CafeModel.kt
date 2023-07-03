package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CafeModel(
    val id: Int,
    val name: String,
    val location: String,
    val description: String,
    val image: String,
    @SerialName("business_hours")
    val businessHours: String,
    val rating: Float? = null
)