package com.example.contract

import com.example.data.model.CategoryModel

interface CategoryContract {
    fun getListCategories(): List<CategoryModel>

    fun getCategory(id: Int): CategoryModel?

}