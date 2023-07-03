package com.example.repository

import com.example.data.entity.DrinksEntity
import com.example.data.model.DrinksModel
import com.example.util.toDrinksModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class DrinksRepository(
    private val database: Database
) {
    fun getListDrinks(): List<DrinksModel> {
        val result = database.from(DrinksEntity)
            .select()
            .map {
                it.toDrinksModel()
            }
        return result
    }

    fun getDrinks(id: Int): DrinksModel? {
        val result = database.from(DrinksEntity)
            .select()
            .where(DrinksEntity.id eq id)
            .map {
                it.toDrinksModel()
            }
        return result.firstOrNull()
    }

    fun updateQuantityDrinks(id: Int, quantity: Int): Boolean {
        val currentQuantity = getCurrentQuantity(id)
        var updateRow = 0

        currentQuantity?.let {
            val updateQuantity = it - quantity
            updateRow = database.update(DrinksEntity) {
                set(DrinksEntity.quantity, updateQuantity)
                where { it.id eq id }
            }
        }
        return updateRow > 0
    }

    private fun getCurrentQuantity(id: Int): Int? {
        return database.from(DrinksEntity)
            .select(DrinksEntity.quantity)
            .where { DrinksEntity.id eq id }
            .map { it[DrinksEntity.quantity] }
            .singleOrNull()
    }
}