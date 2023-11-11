package com.example.data.entity

import org.ktorm.schema.*

object ReviewEntity : Table<Nothing>("review"){
    val id = int("id").primaryKey()
    val drinkId = int("drink_id")
    val userId = int("user_id")
    val rating = float("rating")
    val comment = text("comment")
}