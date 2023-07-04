package com.example.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object CartEntity : Table<Nothing>("cart") {
    val id = int("id").primaryKey()
    val userId = int("user_id")
    val name = varchar("name")
    val date = varchar("date")
}