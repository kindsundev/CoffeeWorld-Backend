package com.example.controller

import com.example.contract.CafeContract
import com.example.data.model.BeverageCategoryModel
import com.example.data.model.CafeModel
import com.example.data.model.CategoryModel

class CafeController(
    private val cafe: CafeContract
) {
     fun getCafeList(): List<CafeModel> = cafe.getCafeList()

    fun getCategoryList(cafeId: Int): List<CategoryModel> {
        return cafe.getCategoryList(cafeId)
    }

    fun getDrinksListInCategory(cafeId: Int, categoryId: Int): BeverageCategoryModel?  {
        return cafe.getDrinksListInCategory(cafeId, categoryId)
    }

    fun updateQuantityDrinks(id: Int, quantity: Int): Boolean {
        return cafe.updateQuantityDrinks(id, quantity)
    }

}