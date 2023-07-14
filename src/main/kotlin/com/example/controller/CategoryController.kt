package com.example.controller

import com.example.contract.CategoryContract
import com.example.data.model.CategoryModel

class CategoryController(
    private val category: CategoryContract
) {
    fun getListCategories(): List<CategoryModel> = category.getListCategories()

    fun getCategory(id: Int): CategoryModel? = category.getCategory(id)

}