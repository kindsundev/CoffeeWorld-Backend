package com.example.repository

import com.example.contract.CafeContract
import com.example.data.entity.CafeEntity
import com.example.data.entity.CategoryEntity
import com.example.data.entity.DrinksEntity
import com.example.data.model.*
import com.example.util.toCafeModel
import com.example.util.toCategoryModel
import com.example.util.toDrinksModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

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

    override fun getDrinksListInCategory(cafeId: Int, categoryId: Int): BeverageCategoryModel? {
        var categoryName: String? = null
        val drinksList = mutableListOf<DrinksModel>()
        database.from(DrinksEntity)
            .innerJoin(CategoryEntity, DrinksEntity.categoryId eq CategoryEntity.id)
            .select()
            .where {
                (DrinksEntity.cafeId eq cafeId) and (DrinksEntity.categoryId eq categoryId)
            }
            .map {
                categoryName= it[CategoryEntity.name]
                val drinksModel = it.toDrinksModel()
                drinksList.add(drinksModel)
            }
        return categoryName?.let { BeverageCategoryModel(it, drinksList) }
    }

    override fun getMenuList(cafeId: Int): List<MenuModel>? {
        val result = mutableListOf<MenuModel>()

        database.from(CategoryEntity).select()
            .where(CategoryEntity.cafeId eq cafeId)
            .map { it.toCategoryModel() }
            .onEach { category ->
                database.from(DrinksEntity).select()
                    .where { (DrinksEntity.cafeId eq cafeId) and (DrinksEntity.categoryId eq category.id) }
                    .map { it.toDrinksModel() }
                    .also { drinkList ->
                        if (drinkList.isNotEmpty()) {
                            result.add(MenuModel(category, drinkList))
                        }
                    }
            }

        return result.ifEmpty { null }
    }


    override fun updateQuantityDrinks(id: Int, quantity: Int): Boolean {
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