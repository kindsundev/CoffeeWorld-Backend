package com.example.data.dto

import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt

@Serializable
data class UserDTO(
    val username: String,
    val password: String,
    val image: String? = null,
    val email: String,
    val name: String,
    val address: String? = null,
    val phone: String? = null
) {

    // the main validation will be on the client (mobile app)
    fun isValidCredentials(): Boolean {
        return (username.length > 4) && (password.length >= 8) && (email.isNotEmpty()) && (name.isNotEmpty())
    }

    fun hashedPassword(): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
}