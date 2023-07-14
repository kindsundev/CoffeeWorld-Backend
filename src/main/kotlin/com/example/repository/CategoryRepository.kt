package com.example.repository

import com.example.contract.CategoryContract
import com.example.data.entity.CategoryEntity
import com.example.data.model.CategoryModel
import com.example.util.toCategoryModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class CategoryRepository(
    private val database: Database
): CategoryContract {
    override fun getListCategories(): List<CategoryModel> {
        return database.from(CategoryEntity)
            .select()
            .map { it.toCategoryModel() }
    }

    override fun getCategory(id: Int): CategoryModel? {
        return database.from(CategoryEntity)
            .select()
            .where(CategoryEntity.id eq id)
            .map { it.toCategoryModel() }
            .firstOrNull()
    }

}