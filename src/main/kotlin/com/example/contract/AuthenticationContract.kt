package com.example.contract

import com.example.data.dto.LoginDTO
import com.example.data.dto.RegisterDTO


interface AuthenticationContract {

    fun register(user: RegisterDTO): Boolean?

    fun login(user: LoginDTO): Boolean?

}