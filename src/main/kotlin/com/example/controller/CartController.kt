package com.example.controller

import com.example.contract.CartContract
import com.example.data.dto.CartDTO
import com.example.data.dto.CartItemDTO
import com.example.data.model.CartModel

class CartController(
    private val cart: CartContract
) {

    fun getCartByUserId(userId: Int): List<CartModel> = cart.getCartByUserId(userId)

    fun createCart(cart: CartDTO): Boolean = this.cart.createCart(cart)

    fun deleteCart(id: Int): Boolean = cart.deleteCart(id)

    fun insertItemToCart(item: CartItemDTO): Boolean = cart.insertItemToCart(item)

    fun deleteItemFromCart(id: Int): Boolean = cart.deleteItemFromCart(id)

    fun updateCartItemQuantity(id: Int, quantity: Int): Boolean = cart.updateCartItemQuantity(id, quantity)

}