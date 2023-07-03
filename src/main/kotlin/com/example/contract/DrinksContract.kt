package com.example.contract

import com.example.data.model.DrinksModel

interface DrinksContract {

    fun getListDrinks(): List<DrinksModel>

    fun getDrinks(id: Int): DrinksModel?

    fun updateQuantityDrinks(id: Int, quantity: Int): Boolean


}