package com.example.contract

import com.example.data.dto.LoginDTO
import com.example.data.dto.AuthDTO
import com.example.data.dto.RegisterDTO

interface AuthContract {
    fun loginUser(user: LoginDTO): Boolean?

    fun registerUser(user: RegisterDTO): String

    fun forgotPassword(authInfo: AuthDTO): String

}