package com.example.controller

import com.example.contract.UserContract
import com.example.data.dto.EmailDTO
import com.example.data.dto.PasswordDTO
import com.example.data.model.UserModel

class UserController(
    private val user: UserContract
) {

    fun getUser(username: String): UserModel? = user.getUser(username)

    fun updateName(username: String, name: String): Boolean {
        return user.updateName(username, name)
    }

    fun updateAddress(username: String, address: String): Boolean {
        return user.updateAddress(username, address)
    }

    fun updatePhone(username: String, phone: String): Boolean {
        return user.updatePhone(username, phone)
    }

    fun updatePassword(authPassword: PasswordDTO): String {
        return user.authAndUpdatePassword(authPassword)
    }

    fun updateEmail(authEmail: EmailDTO): String {
        return user.authAndUpdateEmail(authEmail)
    }

    fun updateAvatar(username: String, image: ByteArray?): Boolean {
        return user.updateAvatar(username, image)
    }

}