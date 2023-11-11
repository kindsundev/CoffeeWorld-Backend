package com.example.controller

import com.example.contract.CafeContract
import com.example.data.model.*

class CafeController(
    private val cafe: CafeContract
) {
     fun getCafeList(): List<CafeModel> = cafe.getCafeList()

    fun getCategoryList(cafeId: Int): List<CategoryModel> {
        return cafe.getCategoryList(cafeId)
    }

    fun getDrinkList(cafeId: Int) = cafe.getDrinkList(cafeId)

    fun getDrinkListByCategory(cafeId: Int, categoryId: Int): List<DrinksModel>  {
        return cafe.getDrinkListByCategory(cafeId, categoryId)
    }

    fun getMenu(cafeId: Int): MenuModel {
        return cafe.getMenu(cafeId)
    }

    fun updateQuantityDrinks(id: Int, quantity: Int): Boolean {
        return cafe.updateQuantityDrinks(id, quantity)
    }

}