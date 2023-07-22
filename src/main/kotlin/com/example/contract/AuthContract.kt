package com.example.contract

import com.example.data.dto.LoginDTO
import com.example.data.dto.AuthDTO
import com.example.data.dto.RegisterDTO
import com.example.data.model.UserModel

interface AuthContract {

    fun loginUser(user: LoginDTO): String?

    fun registerUser(user: RegisterDTO): String

    fun forgotPassword(authInfo: AuthDTO): String

    fun getUser(username: String): UserModel?

}