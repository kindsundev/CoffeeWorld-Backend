package com.example.repository

import com.example.data.dto.LoginDTO
import com.example.data.entity.UserEntity
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt

class LoginRepository(
    private val database: Database
) {

    // the main validation will be on the client (mobile app)
    fun isInvalidCredentials(user: LoginDTO): Boolean {
        return user.username.length < 4 || user.password.length < 8
    }

    fun authenticatePassword(user: LoginDTO): Boolean {
        val hashedPassword = getPasswordOfUser(user.username)
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