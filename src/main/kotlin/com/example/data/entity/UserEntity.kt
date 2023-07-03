package com.example.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserEntity : Table<Nothing>("user") {
    val id = int("id").primaryKey()
    val username = varchar("username")
    val password = varchar("password")
    val image = varchar("image")
    val email = varchar("email")
    val name = varchar("name")
    val address = varchar("address")
    val phone = varchar("phone")
}