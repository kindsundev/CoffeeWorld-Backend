package com.example.util

import com.example.data.entity.*
import com.example.data.model.*
import org.ktorm.dsl.QueryRowSet

fun QueryRowSet.toCafeModel(): CafeModel {
    return CafeModel(
        id = this[CafeEntity.id]!!,
        name = this[CafeEntity.name]!!,
        description = this[CafeEntity.description]!!,
        location = this[CafeEntity.location]!!,
        image = this[CafeEntity.image]!!,
        businessHours = this[CafeEntity.businessHours]!!,
        rating = this[CafeEntity.rating]
    )
}

fun QueryRowSet.toBillModel(): BillModel {
    return BillModel(
        id = this[BillEntity.id]!!,
        userId = this[BillEntity.userId]!!,
        cartId = this[BillEntity.cartId]!!,
        paymentMethodId = this[BillEntity.paymentMethodId]!!,
        date = this[BillEntity.date]!!,
        amount = this[BillEntity.amount]!!,
        completed = this[BillEntity.completed]!!
    )
}

fun QueryRowSet.toCategoryModel(): CategoryModel {
    return CategoryModel(
        id = this[CategoryEntity.id]!!,
        cafeId = this[CategoryEntity.cafeId]!!,
        name = this[CategoryEntity.name]!!,
        image = this[CategoryEntity.image]
    )
}

fun QueryRowSet.toDrinkModel(): DrinkModel {
    return DrinkModel(
        id = this[DrinkEntity.id]!!,
        cafeId = this[DrinkEntity.cafeId]!!,
        name = this[DrinkEntity.name]!!,
        quantity = this[DrinkEntity.quantity]!!,
        price = this[DrinkEntity.price]!!,
        description = this[DrinkEntity.description]!!,
        image = this[DrinkEntity.image],
        categoryId = this[DrinkEntity.categoryId]!!,
        rating = this[DrinkEntity.rating]
    )
}

fun QueryRowSet.toPaymentMethodModel(): PaymentMethodModel {
    return PaymentMethodModel(
        id = this[PaymentMethodEntity.id]!!,
        name = this[PaymentMethodEntity.name]!!,
    )
}

fun QueryRowSet.toReviewModel(): ReviewModel {
    return ReviewModel(
        id = this[ReviewEntity.id]!!,
        drinkId = this[ReviewEntity.drinkId]!!,
        userId = this[ReviewEntity.userId]!!,
        rating = this[ReviewEntity.rating]!!,
        comment = this[ReviewEntity.comment]
    )
}

fun QueryRowSet.toUserModel(): UserModel {
    return UserModel(
        id = this[UserEntity.id]!!,
        username = this[UserEntity.username]!!,
        image = this[UserEntity.image],
        email = this[UserEntity.email]!!,
        name = this[UserEntity.name]!!,
        address = this[UserEntity.address],
        phone = this[UserEntity.phone]
    )
}