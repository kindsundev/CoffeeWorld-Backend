package com.example.repository

import com.example.contract.FavoriteContract
import com.example.data.dto.FavoriteDTO
import com.example.data.entity.DrinkEntity
import com.example.data.entity.FavoriteEntity
import com.example.data.model.DrinkModel
import com.example.util.toDrinkModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class FavoriteRepository(
    private val database: Database
) : FavoriteContract {

    override fun getListFavoriteDrinks(userId: Int): List<DrinkModel> {
        val favoriteDrinksIds = getListFavoriteDrinksId(userId)

        val favoriteDrinks = mutableListOf<DrinkModel>()
        favoriteDrinksIds.forEach { drinkId ->
            val drinks = database.from(DrinkEntity)
                .select()
                .where { DrinkEntity.id eq drinkId }
                .map { it.toDrinkModel() }
                .firstOrNull()

            drinks?.let { favoriteDrinks.add(it) }
        }

        return favoriteDrinks
    }

    private fun getListFavoriteDrinksId(userId: Int): List<Int> {
        return database.from(FavoriteEntity)
            .select(FavoriteEntity.drinkId)
            .where { FavoriteEntity.userId eq userId }
            .map { it[FavoriteEntity.drinkId] }
            .filterNotNull()
    }

    override fun createFavorite(favorite: FavoriteDTO) {
        database.insert(FavoriteEntity) {
            set(FavoriteEntity.userId, favorite.userId)
            set(FavoriteEntity.drinkId, favorite.drinkId)
        }
    }

    override fun deleteFavorite(id: Int): Boolean {
        val deleteRow = database.delete(FavoriteEntity) {
            it.id eq id
        }
        return deleteRow > 0
    }
}