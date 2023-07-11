package com.example.controller

import com.example.contract.UserContract
import com.example.data.dto.EmailDTO
import com.example.data.dto.PasswordDTO
import com.example.data.model.UserModel
import com.example.repository.UserRepository

class UserController(
    private val repository: UserRepository
) : UserContract {

    override fun getUser(username: String): UserModel? = repository.getUser(username)

    override fun updateName(username: String, name: String): Boolean {
        return repository.updateName(username, name)
    }

    override fun updateAddress(username: String, address: String): Boolean {
        return  repository.updateAddress(username, address)
    }

    override fun updatePhone(username: String, phone: String): Boolean {
        return repository.updatePhone(username, phone)
    }

    override fun updatePassword(authPassword: PasswordDTO): String {
        return repository.authAndUpdatePassword(authPassword)
    }

    override fun updateEmail(authEmail: EmailDTO): String {
        return repository.authAndUpdateEmail(authEmail)
    }

    override fun updateAvatar(username: String, base64: String): Boolean {
        return repository.updateAvatar(username, base64)
    }

}