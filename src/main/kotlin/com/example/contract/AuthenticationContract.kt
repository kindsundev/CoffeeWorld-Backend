package com.example.contract

import com.example.data.dto.UserDTO

interface AuthenticationContract {

    fun register(user: UserDTO): Boolean?

}