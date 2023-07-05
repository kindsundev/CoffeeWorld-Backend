package com.example.repository

import com.example.data.dto.BillDTO
import com.example.data.entity.BillEntity
import com.example.data.model.BillModel
import com.example.util.toBillModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class BillRepository(
    private val database: Database
) {
    fun getBillByUserId(userId: Int): List<BillModel> {
        return database.from(BillEntity)
            .select()
            .where { BillEntity.userId eq userId }
            .map { it.toBillModel() }
    }

    fun createBill(bill: BillDTO) {
        database.insert(BillEntity) {
            set(BillEntity.userId, bill.userId)
            set(BillEntity.cartId, bill.cartId)
            set(BillEntity.paymentMethodId, bill.paymentMethodId)
            set(BillEntity.date, bill.date)
            set(BillEntity.amount, bill.amount)
            set(BillEntity.completed, bill.completed)
        }
    }

}