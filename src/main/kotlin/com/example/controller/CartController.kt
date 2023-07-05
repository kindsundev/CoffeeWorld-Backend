package com.example.controller

import com.example.contract.CartContract
import com.example.data.dto.CartDTO
import com.example.data.dto.CartItemDTO
import com.example.data.model.CartModel
import com.example.repository.CartRepository

class CartController(
    private val repository: CartRepository
) : CartContract {

    override fun getCartByUserId(userId: Int): List<CartModel> = repository.getCartByUserId(userId)

    override fun createCart(cart: CartDTO) = repository.createCart(cart)

    override fun deleteCart(id: Int): Boolean = repository.deleteCart(id)

    override fun insertItemToCart(item: CartItemDTO) = repository.insertItemToCart(item)

    override fun deleteItemFromCart(id: Int): Boolean = repository.deleteItemFromCart(id)

    override fun updateCartItemQuantity(id: Int, quantity: Int): Boolean = repository.updateCartItemQuantity(id, quantity)

}