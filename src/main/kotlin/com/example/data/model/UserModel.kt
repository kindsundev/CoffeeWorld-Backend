package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val username: String,
    val image: ByteArray? = null,
    val email: String,
    val name: String,
    val address: String? = null,
    val phone: String? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserModel

        if (id != other.id) return false
        if (username != other.username) return false
        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false
        if (email != other.email) return false
        if (name != other.name) return false
        if (address != other.address) return false
        return phone == other.phone
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + username.hashCode()
        result = 31 * result + (image?.contentHashCode() ?: 0)
        result = 31 * result + email.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (phone?.hashCode() ?: 0)
        return result
    }
}