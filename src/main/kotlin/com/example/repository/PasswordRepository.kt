package com.example.repository

import com.example.data.dto.AuthDTO
import com.example.data.entity.UserEntity
import com.example.data.model.UserModel
import com.example.service.EmailService
import com.example.util.toUserModel
import io.ktor.server.config.*
import org.apache.commons.lang3.RandomStringUtils
import org.ktorm.database.Database
import org.ktorm.dsl.*

class PasswordRepository(
    private val database: Database
) {

    private fun getUserInfo(authInfo: AuthDTO): UserModel? {
        return database.from(UserEntity)
            .select()
            .where { UserEntity.username eq authInfo.username }
            .map { it.toUserModel() }
            .firstOrNull()
    }

    fun isValidAuthInfo(authInfo: AuthDTO): Boolean {
        return getUserInfo(authInfo)?.run {
            username == authInfo.username && email == authInfo.email
        } ?: false
    }

    fun generateRandomPassword(): String {
        val length = 10
        val includeLetters = true
        val includeNumbers = true
        return RandomStringUtils.random(length, includeLetters, includeNumbers)
    }

    fun updatePassword(username: String, password: String): Boolean {
        return database.update(UserEntity) {
            set(UserEntity.password, password)
            where { UserEntity.username eq username }
        } > 0
    }

    fun sendNewPasswordByEmail(authInfo: AuthDTO, newPassword: String, config: HoconApplicationConfig): Boolean {
        val subject = "Reset password - Coffee World Application"
        val message = """
        Dear User,
        
        We have generated a new password for your account:
        
        New Password: $newPassword
        
        Please use this new password to log in to your account. If you have any questions or need further assistance, feel free to contact us.
        
        Best regards,
        Coffee World Application
        """.trimIndent()

        return EmailService(config).sendEmail(authInfo.email, subject, message)
    }

}