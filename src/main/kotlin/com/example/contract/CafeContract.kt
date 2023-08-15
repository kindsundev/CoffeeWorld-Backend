package com.example.contract

import com.example.data.model.CafeModel
import com.example.data.model.CategoryModel
import com.example.data.model.DrinksModel

interface CafeContract {

    fun getCafeList(): List<CafeModel>

    fun getCategoryList(cafeId: Int): List<CategoryModel>

    fun getDrinksListInCategory(cafeId: Int, categoryId: Int): List<DrinksModel>

    fun updateQuantityDrinks(id: Int, quantity: Int): Boolean


}