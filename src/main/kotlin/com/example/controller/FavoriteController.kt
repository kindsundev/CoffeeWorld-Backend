package com.example.controller

import com.example.contract.FavoriteContract
import com.example.data.dto.FavoriteDTO
import com.example.data.model.DrinkModel

class FavoriteController(
    private val service: FavoriteContract
) {

    fun getListFavoriteDrinks(userId: Int): List<DrinkModel> = service.getListFavoriteDrinks(userId)

    fun createFavorite(favorite: FavoriteDTO) = this.service.createFavorite(favorite)

    fun deleteFavorite(id: Int): Boolean = service.deleteFavorite(id)

}