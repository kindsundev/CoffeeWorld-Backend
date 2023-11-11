package com.example.controller

import com.example.contract.AuthContract
import com.example.data.dto.LoginDTO
import com.example.data.dto.AuthDTO
import com.example.data.dto.RegisterDTO
import com.example.data.model.UserModel

class AuthController(
    private val service: AuthContract
){
    fun loginUser(user: LoginDTO): String? = service.loginUser(user)

    fun registerUser(user: RegisterDTO): String = service.registerUser(user)

    fun forgotPassword(authInfo: AuthDTO): String = service.forgotPassword(authInfo)

    fun getUser(username: String): UserModel? = service.getUser(username)
}