package com.example.repository

import com.example.data.dto.CartDTO
import com.example.data.entity.CartEntity
import com.example.data.entity.CartItemEntity
import com.example.data.model.CartItemModel
import com.example.data.model.CartModel
import com.example.util.toCartItemModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class CartRepository(
    private val database: Database
) {

    // cannot use join here because, if pk cart_item.id is null -> tired
    fun getCartByUserId(id: Int): List<CartModel> {
        val carts = mutableListOf<CartModel>()

        database.from(CartEntity)
            .select()
            .where { CartEntity.userId eq id }
            .forEach { query ->
                val cartId = query[CartEntity.id]!!
                val userId = query[CartEntity.userId]!!
                val name = query[CartEntity.name]!!
                val date = query[CartEntity.date]!!

                val cartItems = getListCartItems(cartId)

                val cartModel = CartModel(cartId, userId, name, date, cartItems.ifEmpty { null })
                carts.add(cartModel)
            }

        return carts
    }

    private fun getListCartItems(cartId: Int): List<CartItemModel> {
        val cartItems = mutableListOf<CartItemModel>()
        database.from(CartItemEntity)
            .select()
            .where { CartItemEntity.cartId eq cartId }
            .forEach {
                cartItems.add(it.toCartItemModel())
            }
        return cartItems
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