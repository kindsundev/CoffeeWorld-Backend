package com.example.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.int

object CartItemEntity: Table<Nothing>("cart_item") {
    val id = int("id")
    val cartId = int("cart_id")
    val drinkId = int("drink_id")
    val quantity = int("quantity")
}