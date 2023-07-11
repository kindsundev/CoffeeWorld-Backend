package com.example.contract

import com.example.data.dto.EmailDTO
import com.example.data.dto.PasswordDTO
import com.example.data.model.UserModel

interface UserContract {

    fun getUser(username: String): UserModel?

    fun updateName(username: String, name: String): Boolean

    fun updateAddress(username: String, address: String): Boolean

    fun updatePhone(username: String, phone: String): Boolean

    fun updatePassword(authPassword: PasswordDTO): String

    fun updateEmail(authEmail: EmailDTO): String

    fun updateAvatar(username: String, base64: String): Boolean

}