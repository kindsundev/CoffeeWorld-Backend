package com.example.repository

import com.example.data.dto.RegisterDTO
import com.example.data.entity.UserEntity
import com.example.common.Constants
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt

class RegisterRepository(
    private val database: Database
) {

    // the main validation will be on the client (mobile app)
    fun isInvalidCredentials(user: RegisterDTO): Boolean {
        return (user.username.length < 4) || (user.password.length < 8) ||
                (user.email.isEmpty()) || (user.name.isEmpty())
    }

    fun hasUsernameExist(username: String): Boolean {
        return database.from(UserEntity)
            .select(UserEntity.username)
            .where { UserEntity.username eq username }
            .map { it[UserEntity.username] }
            .firstOrNull() != null
    }

    fun hasEmailExist(email: String): Boolean {
        return database.from(UserEntity)
            .select(UserEntity.email)
            .where { UserEntity.email eq email }
            .map { it[UserEntity.email] }
            .firstOrNull() != null
    }

    fun createUserAccount(user: RegisterDTO): String {
        val username = user.username.lowercase()
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val effectedRow = database.insert(UserEntity) {
            set(UserEntity.username, username)
            set(UserEntity.password, hashedPassword)
            set(UserEntity.email, user.email)
            set(UserEntity.name, user.name)
        }
        return if (effectedRow <= 0) Constants.REGISTER_FAILED else Constants.REGISTER_SUCCESS
    }
}