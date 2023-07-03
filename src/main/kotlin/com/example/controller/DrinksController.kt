package com.example.controller

import com.example.contract.DrinksContract
import com.example.data.model.DrinksModel
import com.example.repository.DrinksRepository

class DrinksController(
    private val repository: DrinksRepository
) : DrinksContract {
    override fun getListDrinks(): List<DrinksModel> = repository.getListDrinks()

    override fun getDrinks(id: Int): DrinksModel? = repository.getDrinks(id)

    override fun updateQuantityDrinks(id: Int, quantity: Int): Boolean = repository.updateQuantityDrinks(id, quantity)

}