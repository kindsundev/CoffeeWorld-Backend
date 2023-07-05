package com.example.repository

import com.example.data.dto.CartDTO
import com.example.data.entity.CartEntity
import com.example.data.entity.CartItemEntity
import com.example.data.model.CartItemModel
import com.example.data.model.CartModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class CartRepository(
    private val database: Database
) {

    fun getCartByUserId(userId: Int): List<CartModel> {
        val cartItems = database.from(CartItemEntity)
            .select()
            .map {
                val cartItemId = it[CartItemEntity.id] ?: -1
                val cartId = it[CartItemEntity.cartId] ?: -1
                val drinksId = it[CartItemEntity.drinksId] ?: -1
                val quantity = it[CartItemEntity.quantity] ?: -1
                CartItemModel(cartItemId, cartId, drinksId, quantity)
            }
            .groupBy { it.cartId }

        return database.from(CartEntity)
            .leftJoin(CartItemEntity, CartEntity.id eq CartItemEntity.cartId)
            .select()
            .where { CartEntity.userId eq userId }
            .map {
                val cartId = it[CartEntity.id]!!
                val name = it[CartEntity.name]!!
                val date = it[CartEntity.date]!!
                val cartItemsForCart = cartItems[cartId]

                CartModel(cartId, userId, name, date, cartItemsForCart)
            }
    }

    fun createCart(cart: CartDTO) {
        database.insert(CartEntity) {
            set(CartEntity.userId, cart.userId)
            set(CartEntity.name, cart.name)
            set(CartEntity.date, cart.date)
        }
    }

    fun deleteCart(id: Int): Boolean {
        val deleteRow = database.delete(CartEntity) {
            it.id eq id
        }
        return deleteRow > 0
    }

}