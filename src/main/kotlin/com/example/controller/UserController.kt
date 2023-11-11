package com.example.controller

import com.example.contract.UserContract
import com.example.data.dto.EmailDTO
import com.example.data.dto.PasswordDTO
import com.example.data.model.UserModel

class UserController(
    private val service: UserContract
) {

    fun getUser(username: String): UserModel? = service.getUser(username)

    fun updateName(username: String, name: String): Boolean {
        return service.updateName(username, name)
    }

    fun updateAddress(username: String, address: String): Boolean {
        return service.updateAddress(username, address)
    }

    fun updatePhone(username: String, phone: String): Boolean {
        return service.updatePhone(username, phone)
    }

    fun updatePassword(authPassword: PasswordDTO): String {
        return service.authAndUpdatePassword(authPassword)
    }

    fun updateEmail(authEmail: EmailDTO): String {
        return service.authAndUpdateEmail(authEmail)
    }

    fun updateAvatar(username: String, image: ByteArray?): Boolean {
        return service.updateAvatar(username, image)
    }

}