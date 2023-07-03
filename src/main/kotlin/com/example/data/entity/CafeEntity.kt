package com.example.data.entity

import org.ktorm.schema.*

object CafeEntity : Table<Nothing>("cafe") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val location = varchar("location")
    val description = text("description")
    val image = varchar("image")
    val businessHours = varchar("business_hours")
    val rating = float("rating")
}