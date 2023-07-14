package com.example.controller

import com.example.contract.FavoriteContract
import com.example.data.dto.FavoriteDTO
import com.example.data.model.DrinksModel

class FavoriteController(
    private val favorite: FavoriteContract
) {

    fun getListFavoriteDrinks(userId: Int): List<DrinksModel> = favorite.getListFavoriteDrinks(userId)

    fun createFavorite(favorite: FavoriteDTO) = this.favorite.createFavorite(favorite)

    fun deleteFavorite(id: Int): Boolean = favorite.deleteFavorite(id)

}