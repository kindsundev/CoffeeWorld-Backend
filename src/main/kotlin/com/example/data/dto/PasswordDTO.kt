package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PasswordDTO(
    val username: String,
    @SerialName("old_password")
    val oldPassword: String,
    @SerialName("new_password")
    val newPassword: String
)