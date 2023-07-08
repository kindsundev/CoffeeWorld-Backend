package com.example.repository

import com.example.data.dto.LoginDTO
import com.example.data.dto.RegisterDTO
import com.example.data.entity.UserEntity
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt

class AuthenticationRepository(
    private val database: Database
) {
    fun register(user: RegisterDTO): Boolean? {
        if (!(isValidCredentials(user))) return null
        if (checkUserExist(user.username)) return false
        return createUserAccount(user)
    }

    // the main validation will be on the client (mobile app)
    private fun isValidCredentials(user: RegisterDTO): Boolean {
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

    private fun createUserAccount(user: RegisterDTO): Boolean {
        val username = user.username.lowercase()
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        return database.insert(UserEntity) {
            set(UserEntity.username, username)
            set(UserEntity.password, hashedPassword)
            set(UserEntity.email, user.email)
            set(UserEntity.name, user.name)
        } > 0
    }

    fun login(user: LoginDTO): Boolean? {
        if (user.username.length < 4 || user.password.length < 8) return null
        val hashedPassword = getPasswordOfUser(user.username) ?: return false
        return BCrypt.checkpw(user.password, hashedPassword)
    }

    private fun getPasswordOfUser(username : String) : String? {
        return database.from(UserEntity)
            .select(UserEntity.password)
            .where { UserEntity.username eq username }
            .map { it[UserEntity.password] }
            .firstOrNull()
    }
}