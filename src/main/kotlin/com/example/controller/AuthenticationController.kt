package com.example.controller

import com.example.contract.AuthenticationContract
import com.example.data.dto.LoginDTO
import com.example.data.dto.RegisterDTO
import com.example.repository.AuthenticationRepository

class AuthenticationController(
    private val repository: AuthenticationRepository
) : AuthenticationContract {

    override fun register(user: RegisterDTO): Boolean? = repository.register(user)

    override fun login(user: LoginDTO): Boolean? = repository.login(user)

}