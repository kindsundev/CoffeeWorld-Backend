package com.example.repository

import com.example.common.Constants
import com.example.data.dto.EmailDTO
import com.example.data.dto.PasswordDTO
import com.example.data.entity.UserEntity
import com.example.data.model.UserModel
import com.example.util.toUserModel
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.mindrot.jbcrypt.BCrypt

class UserRepository(
    private val database: Database
) {

    fun getUser(username: String): UserModel? {
        return database.from(UserEntity)
            .select()
            .where { UserEntity.username eq username }
            .map { it.toUserModel() }
            .firstOrNull()
    }

    fun updateAvatar(username: String, base64: String): Boolean {
        return database.update(UserEntity) {
            set(UserEntity.image, base64)
            where { UserEntity.username eq username }
        } > 0
    }

    fun updateName(username: String, name: String): Boolean {
        return database.update(UserEntity) {
            set(UserEntity.name, name)
            where { UserEntity.username eq username }
        } > 0
    }

    fun updateAddress(username: String, address: String): Boolean {
        return database.update(UserEntity) {
            set(UserEntity.address, address)
            where { UserEntity.username eq username }
        } > 0
    }

    fun updatePhone(username: String, phone: String): Boolean {
        return database.update(UserEntity) {
            set(UserEntity.phone, phone)
            where { UserEntity.username eq username }
        } > 0
    }

    fun authAndUpdatePassword(authPassword: PasswordDTO): String {
        return when (authenticateUser(authPassword.username, authPassword.oldPassword)) {
            null -> Constants.AUTH_FAILED
            false -> Constants.INCORRECT_PASSWORD
            else -> {
                if (authPassword.newPassword.length < 8) {
                    Constants.WARN_PASSWORD_LENGTH
                } else {
                    updatePassword(authPassword.username, authPassword.newPassword)
                }
            }
        }
    }

    fun authAndUpdateEmail(authEmail: EmailDTO): String {
        return when (authenticateUser(authEmail.username, authEmail.password)) {
            null -> Constants.AUTH_FAILED
            false -> Constants.INCORRECT_PASSWORD
            else -> {
                if (hasEmailExist(authEmail.email)) {
                    Constants.EMAIL_EXIST
                } else {
                    updateEmail(authEmail.username, authEmail.email)
                }
            }
        }
    }

    private fun hasEmailExist(email: String): Boolean {
        return database.from(UserEntity)
            .select(UserEntity.email)
            .where { UserEntity.email eq email }
            .map { it[UserEntity.email] }
            .firstOrNull() != null
    }

    private fun authenticateUser(username: String, oldPassword: String): Boolean? {
        val currentPassword = database.from(UserEntity)
            .select(UserEntity.password)
            .where { UserEntity.username eq username }
            .map { it[UserEntity.password] }
            .firstOrNull() ?: return null
        return BCrypt.checkpw(oldPassword, currentPassword)
    }

    private fun updatePassword(username: String, newPassword: String): String {
        val hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt())
        val updateRow = database.update(UserEntity) {
            set(UserEntity.password, hashedPassword)
            where { UserEntity.username eq username }
        }
        return if (updateRow > 0) Constants.UPDATED_PASSWORD else Constants.UPDATE_PASSWORD_FAILED
    }

    private fun updateEmail(username: String, email: String): String {
        val updatedRow = database.update(UserEntity) {
            set(UserEntity.email, email)
            where { UserEntity.username eq username }
        }
        return if (updatedRow > 0) Constants.UPDATED_EMAIL else Constants.UPDATE_EMAIL_FAILED
    }
}