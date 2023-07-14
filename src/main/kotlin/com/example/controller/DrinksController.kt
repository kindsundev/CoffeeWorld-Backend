package com.example.controller

import com.example.contract.DrinksContract
import com.example.data.model.DrinksModel

class DrinksController(
    private val drinks: DrinksContract
) {
    fun getListDrinks(): List<DrinksModel> = drinks.getListDrinks()

    fun getDrinks(id: Int): DrinksModel? = drinks.getDrinks(id)

    fun updateQuantityDrinks(id: Int, quantity: Int): Boolean = drinks.updateQuantityDrinks(id, quantity)

}