package com.example.repository

import com.example.data.dto.UserDTO
import com.example.data.entity.UserEntity
import org.ktorm.database.Database
import org.ktorm.dsl.*

class AuthenticationRepository(
    private val database: Database
) {
    fun register(user: UserDTO): Boolean? {
        if (!(user.isValidCredentials())) return null
        if (alreadyRegistered(user.username)) return false
        return createUser(user)
    }

    private fun alreadyRegistered(username: String): Boolean {
        return database.from(UserEntity)
            .select(UserEntity.username)
            .where { UserEntity.username eq username }
            .map { it[UserEntity.username] }
            .firstOrNull() != null
    }

    private fun createUser(user: UserDTO): Boolean {
        val username = user.username.lowercase()
        val hashedPassword = user.hashedPassword()
        return database.insert(UserEntity) {
            set(UserEntity.username, username)
            set(UserEntity.password, hashedPassword)
            set(UserEntity.image, user.image)
            set(UserEntity.email, user.email)
            set(UserEntity.name, user.name)
            set(UserEntity.address, user.address)
            set(UserEntity.phone, user.phone)
        } > 0
    }

}