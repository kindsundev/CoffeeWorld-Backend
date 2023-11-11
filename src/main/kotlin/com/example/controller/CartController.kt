package com.example.controller

import com.example.contract.CartContract
import com.example.data.dto.CartDTO
import com.example.data.dto.CartItemDTO
import com.example.data.model.CartModel

class CartController(
    private val service: CartContract
) {

    fun getCartByUserId(userId: Int): List<CartModel> = service.getCartByUserId(userId)

    fun createCart(cart: CartDTO): Boolean = this.service.createCart(cart)

    fun deleteCart(id: Int): Boolean = service.deleteCart(id)

    fun insertItemToCart(item: CartItemDTO): Boolean = service.insertItemToCart(item)

    fun deleteItemFromCart(id: Int): Boolean = service.deleteItemFromCart(id)

    fun updateCartItemQuantity(id: Int, quantity: Int): Boolean = service.updateCartItemQuantity(id, quantity)

}