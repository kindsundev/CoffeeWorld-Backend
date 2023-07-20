package com.example.data.dto

import com.example.data.model.UserModel
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO (
    val token: String?,
    val user: UserModel?
)