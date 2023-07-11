package com.example.repository

import com.example.data.dto.CartDTO
import com.example.data.dto.CartItemDTO
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

        val cartItemsMap = cartItems.groupBy { it.cartId }

        return database.from(CartEntity)
            .select()
            .where { CartEntity.userId eq userId }
            .map {
                val cartId = it[CartEntity.id]!!
                val name = it[CartEntity.name]!!
                val date = it[CartEntity.date]!!
                val cartItemsForCart = cartItemsMap[cartId] ?: emptyList()

                CartModel(cartId, userId, name, date, cartItemsForCart)
            }
    }


    fun createCart(cart: CartDTO): Boolean {
        return database.insert(CartEntity) {
            set(CartEntity.userId, cart.userId)
            set(CartEntity.name, cart.name)
            set(CartEntity.date, cart.date)
        } > 0
    }

    fun deleteCart(id: Int): Boolean {
        database.delete(CartItemEntity) { CartItemEntity.cartId eq id }
        return database.delete(CartEntity) {
            it.id eq id
        } > 0
    }

    fun insertItemToCart(item: CartItemDTO): Boolean {
        return database.insert(CartItemEntity) {
            set(CartItemEntity.cartId, item.cartId)
            set(CartItemEntity.drinksId, item.drinksId)
            set(CartItemEntity.quantity, item.quantity)
        } > 0
    }

    fun deleteItemFromCart(id: Int): Boolean {
        return database.delete(CartItemEntity) { it.id eq id } > 0
    }

    fun updateCartItemQuantity(id: Int, quantity: Int): Boolean {
        return database.update(CartItemEntity) {
            set(CartItemEntity.quantity, quantity)
            where { CartItemEntity.id eq id }
        } > 0
    }
}