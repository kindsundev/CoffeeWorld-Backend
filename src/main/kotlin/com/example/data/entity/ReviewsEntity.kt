package com.example.data.entity

import org.ktorm.schema.*

object ReviewsEntity : Table<Nothing>("reviews"){
    val id = int("id").primaryKey()
    val drinksId = int("drinks_id")
    val userId = int("user_id")
    val rating = float("rating")
    val comment = text("comment")
}