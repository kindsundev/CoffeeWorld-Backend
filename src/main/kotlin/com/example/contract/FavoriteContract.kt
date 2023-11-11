package com.example.contract

import com.example.data.dto.FavoriteDTO
import com.example.data.model.DrinkModel

interface FavoriteContract {

    fun getListFavoriteDrinks(userId: Int): List<DrinkModel>

    fun createFavorite(favorite: FavoriteDTO)

    fun deleteFavorite(id: Int): Boolean

}