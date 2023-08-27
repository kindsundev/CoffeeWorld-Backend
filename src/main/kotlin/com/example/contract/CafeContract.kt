package com.example.contract

import com.example.data.model.BeverageCategoryModel
import com.example.data.model.CafeModel
import com.example.data.model.CategoryModel

interface CafeContract {

    fun getCafeList(): List<CafeModel>

    fun getCategoryList(cafeId: Int): List<CategoryModel>

    fun getDrinksListInCategory(cafeId: Int, categoryId: Int): BeverageCategoryModel?

    fun updateQuantityDrinks(id: Int, quantity: Int): Boolean


}