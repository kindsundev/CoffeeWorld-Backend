package com.example.data.entity

import com.example.data.entity.CafeEntity.primaryKey
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object PaymentMethodEntity : Table<Nothing>("payment_method") {
    val id = int("id").primaryKey()
    val name = varchar("name")
}