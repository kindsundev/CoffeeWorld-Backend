package com.example.repository

import com.example.data.dto.LoginDTO
import com.example.data.dto.RegisterDTO
import com.example.common.Constants

class AuthRepository(
    private val loginRepo: LoginRepository,
    private val registerRepo: RegisterRepository
) {

    fun registerUser(user: RegisterDTO): String {
        return when {
            registerRepo.invalidCredentials(user) -> Constants.INVALID_USER_DATA
            registerRepo.hasUsernameExist(user.username) -> Constants.USERNAME_EXIST
            registerRepo.hasEmailExist(user.email) -> Constants.EMAIL_EXIST
            else -> registerRepo.createUserAccount(user)
        }
    }

    fun loginUser(user: LoginDTO): Boolean? {
        return if (loginRepo.invalidCredentials(user)) null else loginRepo.authenticatePassword(user)
    }

}