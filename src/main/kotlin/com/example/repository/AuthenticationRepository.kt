package com.example.repository

import com.example.data.dto.UserDTO
import com.example.data.entity.UserEntity
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt

class AuthenticationRepository(
    private val database: Database
) {
    fun register(user: UserDTO): Boolean? {
        if (!(isValidCredentials(user))) return null
        if (checkUserExist(user.username)) return false
        return createUserAccount(user)
    }

    // the main validation will be on the client (mobile app)
    private fun isValidCredentials(user: UserDTO): Boolean {
        return (user.username.length > 4) && (user.password.length >= 8)
                && (user.email.isNotEmpty()) && (user.name.isNotEmpty())
    }

    private fun checkUserExist(username: String): Boolean {
        return database.from(UserEntity)
            .select(UserEntity.username)
            .where { UserEntity.username eq username }
            .map { it[UserEntity.username] }
            .firstOrNull() != null
    }

    private fun createUserAccount(user: UserDTO): Boolean {
        val username = user.username.lowercase()
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
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

    fun login(username: String, password: String): Boolean? {
        if (username.length < 4 || password.length < 8) return null
        val hashedPassword = getPasswordOfUser(username) ?: return false
        return BCrypt.checkpw(password, hashedPassword)
    }

    private fun getPasswordOfUser(username : String) : String? {
        return database.from(UserEntity)
            .select(UserEntity.password)
            .where { UserEntity.username eq username }
            .map { it[UserEntity.password] }
            .firstOrNull()
    }
}