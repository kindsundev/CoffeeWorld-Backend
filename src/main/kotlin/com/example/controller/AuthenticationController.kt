package com.example.controller

import com.example.contract.AuthenticationContract
import com.example.data.dto.UserDTO
import com.example.repository.AuthenticationRepository

class AuthenticationController(
    private val repository: AuthenticationRepository
) : AuthenticationContract {

    override fun register(user: UserDTO): Boolean? = repository.register(user)

}