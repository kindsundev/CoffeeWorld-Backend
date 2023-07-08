package com.example.controller

import com.example.contract.AuthContract
import com.example.data.dto.LoginDTO
import com.example.data.dto.RegisterDTO
import com.example.repository.AuthRepository

class AuthController(
    private val repository: AuthRepository
) : AuthContract {
    override fun loginUser(user: LoginDTO): Boolean? = repository.loginUser(user)

    override fun registerUser(user: RegisterDTO): String = repository.registerUser(user)

    override fun forgotPassword(username: String, email: String): String? {
        TODO("Not yet implemented")
    }
}