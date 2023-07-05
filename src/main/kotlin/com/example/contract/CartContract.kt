package com.example.contract

import com.example.data.dto.CartDTO
import com.example.data.dto.CartItemDTO
import com.example.data.model.CartModel

interface CartContract {

    fun getCartByUserId(userId: Int): List<CartModel>

    fun createCart(cart: CartDTO)

    fun deleteCart(id: Int): Boolean

    fun insertItemToCart( item: CartItemDTO)

    fun deleteItemFromCart(id: Int) : Boolean

    fun updateCartItemQuantity(id: Int, quantity: Int): Boolean
}
