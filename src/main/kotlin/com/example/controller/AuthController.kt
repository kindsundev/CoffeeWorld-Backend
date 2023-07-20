package com.example.controller

import com.example.contract.AuthContract
import com.example.data.dto.LoginDTO
import com.example.data.dto.AuthDTO
import com.example.data.dto.RegisterDTO
import com.example.data.model.UserModel

class AuthController(
    private val auth: AuthContract
){
    fun loginUser(user: LoginDTO): Boolean? = auth.loginUser(user)

    fun registerUser(user: RegisterDTO): String = auth.registerUser(user)

    fun forgotPassword(authInfo: AuthDTO): String = auth.forgotPassword(authInfo)

    fun getUser(username: String): UserModel? = auth.getUser(username)
}