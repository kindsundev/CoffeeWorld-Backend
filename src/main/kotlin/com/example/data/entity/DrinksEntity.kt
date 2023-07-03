package com.example.data.entity

import org.ktorm.schema.*

object DrinksEntity : Table<Nothing>("drinks") {
    val id = int("id").primaryKey()
    val cafeId = int("cafe_id")
    val name = varchar("name")
    val quantity = int("quantity")
    val price = double("price")
    val description = varchar("description")
    val image = varchar("image")
    val categoryId = int("category_id")
    val rating = float("rating")
}