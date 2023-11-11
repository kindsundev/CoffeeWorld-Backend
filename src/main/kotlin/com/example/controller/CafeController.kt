package com.example.controller

import com.example.contract.CafeContract
import com.example.data.model.*

class CafeController(
    private val service: CafeContract
) {
     fun getCafeList(): List<CafeModel> = service.getCafeList()

    fun getCategoryList(cafeId: Int): List<CategoryModel> {
        return service.getCategoryList(cafeId)
    }

    fun getDrinkList(cafeId: Int) = service.getDrinkList(cafeId)

    fun getDrinkListByCategory(cafeId: Int, categoryId: Int): List<DrinkModel>  {
        return service.getDrinkListByCategory(cafeId, categoryId)
    }

    fun getMenu(cafeId: Int): MenuModel {
        return service.getMenu(cafeId)
    }

    fun updateQuantityDrink(id: Int, quantity: Int): Boolean {
        return service.updateQuantityDrink(id, quantity)
    }

}