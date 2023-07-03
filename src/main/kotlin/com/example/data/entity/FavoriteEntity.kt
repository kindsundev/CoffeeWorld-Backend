package com.example.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int

object FavoriteEntity : Table<Nothing>("favorite") {
    val id = int("id").primaryKey()
    val userId = int("user_id")
    val drinksId = int("drinks_id")
}