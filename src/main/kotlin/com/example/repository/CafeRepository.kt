package com.example.repository

import com.example.contract.CafeContract
import com.example.data.entity.CafeEntity
import com.example.data.entity.CategoryEntity
import com.example.data.entity.DrinkEntity
import com.example.data.model.*
import com.example.util.toCafeModel
import com.example.util.toCategoryModel
import com.example.util.toDrinkModel
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.schema.ColumnDeclaring

class CafeRepository(
    private val database: Database
): CafeContract {

    override fun getCafeList(): List<CafeModel> {
        val result = database.from(CafeEntity)
            .select()
            .map {
                it.toCafeModel()
            }
        return result
    }

    override fun getCategoryList(cafeId: Int): List<CategoryModel> {
        return database.from(CategoryEntity)
            .select()
            .where(CategoryEntity.cafeId eq cafeId)
            .map { it.toCategoryModel() }
    }

    override fun getDrinkList(cafeId: Int): List<DrinkModel> {
        return handleGetDrinkList(DrinkEntity.cafeId eq cafeId)
    }

    override fun getDrinkListByCategory(cafeId: Int, categoryId: Int): List<DrinkModel> {
        return handleGetDrinkList(
            (DrinkEntity.cafeId eq cafeId) and (DrinkEntity.categoryId eq categoryId)
        )
    }

    private fun handleGetDrinkList(condition: ColumnDeclaring<Boolean>): List<DrinkModel> {
        return mutableListOf<DrinkModel>().apply {
            database.from(DrinkEntity).select()
                .where (condition)
                .map {
                    add(it.toDrinkModel())
                }
        }
    }

    override fun getMenu(cafeId: Int): MenuModel {
        val items = mutableListOf<BeverageCategory>()

        database.from(CategoryEntity).select()
            .where(CategoryEntity.cafeId eq cafeId)
            .map { it.toCategoryModel() }
            .onEach { category ->
                database.from(DrinkEntity).select()
                    .where { (DrinkEntity.cafeId eq cafeId) and (DrinkEntity.categoryId eq category.id) }
                    .map { it.toDrinkModel() }
                    .also { drinkList ->
                        if (drinkList.isNotEmpty()) {
                            items.add(BeverageCategory(cafeId, category, drinkList))
                        }
                    }
            }

        return MenuModel(cafeId, items)
    }


    override fun updateQuantityDrink(id: Int, quantity: Int): Boolean {
        val currentQuantity = getCurrentQuantity(id)
        var updateRow = 0

        currentQuantity?.let {
            val updateQuantity = it - quantity
            updateRow = database.update(DrinkEntity) {
                set(DrinkEntity.quantity, updateQuantity)
                where { it.id eq id }
            }
        }
        return updateRow > 0
    }

    private fun getCurrentQuantity(id: Int): Int? {
        return database.from(DrinkEntity)
            .select(DrinkEntity.quantity)
            .where { DrinkEntity.id eq id }
            .map { it[DrinkEntity.quantity] }
            .singleOrNull()
    }

}