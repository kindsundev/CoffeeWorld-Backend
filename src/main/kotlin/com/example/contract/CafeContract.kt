package com.example.contract

import com.example.data.model.*

interface CafeContract {

    fun getCafeList(): List<CafeModel>

    fun getCategoryList(cafeId: Int): List<CategoryModel>

    fun getDrinkList(cafeId: Int): List<DrinkModel>

    fun getDrinkListByCategory(cafeId: Int, categoryId: Int): List<DrinkModel>

    fun getMenu(cafeId: Int): MenuModel

    fun updateQuantityDrink(id: Int, quantity: Int): Boolean

}