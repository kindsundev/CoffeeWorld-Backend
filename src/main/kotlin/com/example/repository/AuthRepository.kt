package com.example.repository

import com.example.data.dto.LoginDTO
import com.example.data.dto.RegisterDTO
import com.example.common.Constants
import com.example.contract.AuthContract
import com.example.data.dto.AuthDTO
import com.example.data.model.UserModel
import io.ktor.server.config.*
import org.mindrot.jbcrypt.BCrypt

class AuthRepository(
    private val loginRepo: LoginRepository,
    private val registerRepo: RegisterRepository,
    private val passwordRepo: PasswordRepository,
    private val config: HoconApplicationConfig
) : AuthContract {

    override fun registerUser(user: RegisterDTO): String {
        return when {
            registerRepo.isInvalidCredentials(user) -> Constants.INVALID_USER_DATA
            registerRepo.hasUsernameExist(user.username) -> Constants.USERNAME_EXIST
            registerRepo.hasEmailExist(user.email) -> Constants.EMAIL_EXIST
            else -> registerRepo.createUserAccount(user)
        }
    }

    override fun loginUser(user: LoginDTO): Boolean? {
        return if (loginRepo.isInvalidCredentials(user)) null else loginRepo.authenticatePassword(user)
    }

    override fun forgotPassword(authInfo: AuthDTO): String {
        if (passwordRepo.isValidAuthInfo(authInfo)) {
            val newPassword = passwordRepo.generateRandomPassword()
            val hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt())
            val updated = passwordRepo.updatePassword(authInfo.username, hashedPassword)
            return if (updated) {
                val send = passwordRepo.sendNewPasswordByEmail(authInfo, newPassword, config)
                if (send) {
                    Constants.SEND_EMAIL_SUCCESS
                } else {
                    Constants.SEND_EMAIL_FAILED
                }
            } else {
                Constants.UPDATE_PASSWORD_FAILED
            }
        }
        return Constants.INVALID_USER_DATA
    }

    override fun getUser(username: String): UserModel? = loginRepo.getUser(username)

}