package com.example.contract

import com.example.data.dto.UserDTO
import com.example.data.model.UserModel

interface AuthenticationContract {

    fun register(user: UserDTO): Boolean?

    fun login(username: String, password: String): Boolean?

}