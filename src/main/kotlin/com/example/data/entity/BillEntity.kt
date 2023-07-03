package com.example.data.entity

import org.ktorm.schema.Table
import org.ktorm.schema.double
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object BillEntity: Table<Nothing>("bill") {
    val id = int("id").primaryKey()
    val userId = int("user_id")
    val cartId = int("cart_id")
    val paymentMethodId = int("payment_method_id")
    val date = varchar("date")
    val amount = double("amount")
    val completed = varchar("completed")
}


