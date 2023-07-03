package com.example.controller

import com.example.data.model.DrinksModel
import com.example.repository.DrinksRepository

class DrinksController(
    private val drinksRepository: DrinksRepository
) {
    fun getListDrinks(): List<DrinksModel> = drinksRepository.getListDrinks()

    fun getDrinks(id: Int): DrinksModel? = drinksRepository.getDrinks(id)

    fun updateQuantityDrinks( id: Int, quantity: Int): Boolean = drinksRepository.updateQuantityDrinks(id, quantity)

}