package com.example.data.entity

import org.ktorm.schema.*

object UserEntity : Table<Nothing>("user") {
    val id = int("id").primaryKey()
    val username = varchar("username")
    val password = varchar("password")
    val image = blob("image")
    val email = varchar("email")
    val name = varchar("name")
    val address = varchar("address")
    val phone = varchar("phone")
}