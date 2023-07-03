package com.example.controller

import com.example.data.model.CategoryModel
import com.example.repository.CategoryRepository

class CategoryController(
    private val categoryRepository: CategoryRepository
) {
    fun getListCategories(): List<CategoryModel> = categoryRepository.getListCategories()

    fun getCategory(id: Int): CategoryModel? = categoryRepository.getCategory(id)

}