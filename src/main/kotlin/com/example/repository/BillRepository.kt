package com.example.repository

import com.example.contract.BillContract
import com.example.data.dto.BillDTO
import com.example.data.entity.BillEntity
import com.example.data.entity.CartEntity
import com.example.data.entity.CartItemEntity
import com.example.data.model.BillModel
import com.example.util.toBillModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class BillRepository(
    private val database: Database
) : BillContract {

    override fun getBillByUserId(userId: Int): List<BillModel> {
        return database.from(BillEntity)
            .select()
            .where { BillEntity.userId eq userId }
            .map { it.toBillModel() }
    }

    override fun createBill(bill: BillDTO): BillModel? {
        if (!checkUserCart(bill.userId, bill.cartId)) return null
        if (!saveBillToDatabase(bill)) return null
        return getBillByCartId(bill.cartId)
    }

    private fun checkUserCart(userId: Int, cartId: Int): Boolean {
        return database.from(CartEntity)
            .innerJoin(CartItemEntity, CartEntity.id eq CartItemEntity.cartId)
            .select()
            .where { (CartEntity.userId eq userId) and (CartEntity.id eq cartId) }
            .map { it[CartEntity.id] }
            .firstOrNull() != null
    }

    private fun saveBillToDatabase(bill: BillDTO): Boolean {
        return database.insert(BillEntity) {
            set(BillEntity.userId, bill.userId)
            set(BillEntity.cartId, bill.cartId)
            set(BillEntity.paymentMethodId, bill.paymentMethodId)
            set(BillEntity.date, bill.date)
            set(BillEntity.amount, bill.amount)
            set(BillEntity.completed, bill.completed)
        } > 0
    }

    private fun getBillByCartId(id: Int): BillModel {
        return database.from(BillEntity)
            .select()
            .where { BillEntity.cartId eq id }
            .map { it.toBillModel() }
            .first()
    }
}