package com.example.controller

import com.example.contract.CategoryContract
import com.example.data.model.CategoryModel
import com.example.repository.CategoryRepository

class CategoryController(
    private val repository: CategoryRepository
) : CategoryContract {
    override fun getListCategories(): List<CategoryModel> = repository.getListCategories()

    override fun getCategory(id: Int): CategoryModel? = repository.getCategory(id)

}