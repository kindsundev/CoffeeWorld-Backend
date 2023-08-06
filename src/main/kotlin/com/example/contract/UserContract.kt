package com.example.contract

import com.example.data.dto.EmailDTO
import com.example.data.dto.PasswordDTO
import com.example.data.model.UserModel

interface UserContract {

    fun getUser(username: String): UserModel?

    fun updateName(username: String, name: String): Boolean

    fun updateAddress(username: String, address: String): Boolean

    fun updatePhone(username: String, phone: String): Boolean

    fun authAndUpdatePassword(authPassword: PasswordDTO): String

    fun authAndUpdateEmail(authEmail: EmailDTO): String

    fun updateAvatar(username: String, image: ByteArray?): Boolean

}