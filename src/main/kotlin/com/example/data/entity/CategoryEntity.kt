package com.example.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.blob
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object CategoryEntity : Table<Nothing>("category") {
    val id = int("id").primaryKey()
    val cafeId = int("cafe_id")
    val name = varchar("name")
    val image = blob("image")
}