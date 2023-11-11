package com.example.contract

import com.example.data.model.*

interface CafeContract {

    fun getCafeList(): List<CafeModel>

    fun getCategoryList(cafeId: Int): List<CategoryModel>

    fun getDrinkList(cafeId: Int): List<DrinksModel>

    fun getDrinkListByCategory(cafeId: Int, categoryId: Int): List<DrinksModel>

    fun getMenu(cafeId: Int): MenuModel

    fun updateQuantityDrinks(id: Int, quantity: Int): Boolean

}