package com.example.controller

import com.example.contract.FavoriteContract
import com.example.data.dto.FavoriteDTO
import com.example.data.model.DrinksModel
import com.example.repository.FavoriteRepository

class FavoriteController(
    private val repository: FavoriteRepository
) : FavoriteContract {

    override fun getListFavoriteDrinks(userId: Int): List<DrinksModel> = repository.getListFavoriteDrinks(userId)

    override fun createFavorite(favorite: FavoriteDTO) = repository.createFavorite(favorite)

    override fun deleteFavorite(id: Int): Boolean = repository.deleteFavorite(id)

}