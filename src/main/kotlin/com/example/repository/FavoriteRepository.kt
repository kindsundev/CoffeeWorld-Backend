package com.example.repository

import com.example.data.dto.FavoriteDTO
import com.example.data.entity.DrinksEntity
import com.example.data.entity.FavoriteEntity
import com.example.data.model.DrinksModel
import com.example.util.toDrinksModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class FavoriteRepository(
    private val database: Database
) {

    fun getListFavoriteDrinks(userId: Int): List<DrinksModel> {
        val favoriteDrinksIds = getListFavoriteDrinksId(userId)

        val favoriteDrinks = mutableListOf<DrinksModel>()
        favoriteDrinksIds.forEach { drinksId ->
            val drinks = database.from(DrinksEntity)
                .select()
                .where { DrinksEntity.id eq drinksId }
                .map { it.toDrinksModel() }
                .firstOrNull()

            drinks?.let { favoriteDrinks.add(it) }
        }

        return favoriteDrinks
    }

    private fun getListFavoriteDrinksId(userId: Int): List<Int> {
        return database.from(FavoriteEntity)
            .select(FavoriteEntity.drinksId)
            .where { FavoriteEntity.userId eq userId }
            .map { it[FavoriteEntity.drinksId] }
            .filterNotNull()
    }

    fun createFavorite(favorite: FavoriteDTO) {
        database.insert(FavoriteEntity) {
            set(FavoriteEntity.userId, favorite.userId)
            set(FavoriteEntity.drinksId, favorite.drinksId)
        }
    }

    fun deleteFavorite(id: Int): Boolean {
        val deleteRow = database.delete(FavoriteEntity) {
            it.id eq id
        }
        return deleteRow > 0
    }
}