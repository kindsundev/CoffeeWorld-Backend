package com.example.contract

import com.example.data.model.BeverageCategoryModel
import com.example.data.model.CafeModel
import com.example.data.model.CategoryModel
import com.example.data.model.MenuModel

interface CafeContract {

    fun getCafeList(): List<CafeModel>

    fun getCategoryList(cafeId: Int): List<CategoryModel>

    fun getDrinksListInCategory(cafeId: Int, categoryId: Int): BeverageCategoryModel?

    fun getMenuList(cafeId: Int): List<MenuModel>?

    fun updateQuantityDrinks(id: Int, quantity: Int): Boolean


}