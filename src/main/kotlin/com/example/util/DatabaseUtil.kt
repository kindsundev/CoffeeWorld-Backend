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
        name = this[CategoryEntity.name]!!
    )
}

fun QueryRowSet.toDrinksModel(): DrinksModel {
    return DrinksModel(
        id = this[DrinksEntity.id]!!,
        cafeId = this[DrinksEntity.cafeId]!!,
        name = this[DrinksEntity.name]!!,
        quantity = this[DrinksEntity.quantity]!!,
        price = this[DrinksEntity.price]!!,
        description = this[DrinksEntity.description]!!,
        image = this[DrinksEntity.image],
        categoryId = this[DrinksEntity.categoryId]!!,
        rating = this[DrinksEntity.rating]
    )
}

fun QueryRowSet.toFavoriteModel(): FavoriteModel {
    return FavoriteModel(
        id = this[FavoriteEntity.id]!!,
        userId = this[FavoriteEntity.userId]!!,
        drinksId = this[FavoriteEntity.drinksId]!!
    )
}

fun QueryRowSet.toPaymentMethodModel(): PaymentMethodModel {
    return PaymentMethodModel(
        id = this[PaymentMethodEntity.id]!!,
        name = this[PaymentMethodEntity.name]!!,
    )
}

fun QueryRowSet.toReviewsModel(): ReviewsModel {
    return ReviewsModel(
        id = this[ReviewsEntity.id]!!,
        drinksId = this[ReviewsEntity.drinksId]!!,
        userId = this[ReviewsEntity.userId]!!,
        rating = this[ReviewsEntity.rating]!!,
        comment = this[ReviewsEntity.comment]
    )
}

fun QueryRowSet.toUserModel(): UserModel {
    return UserModel(
        id = this[UserEntity.id]!!,
        username = this[UserEntity.name]!!,
        password = this[UserEntity.password]!!,
        image = this[UserEntity.image],
        email = this[UserEntity.email]!!,
        name = this[UserEntity.name]!!,
        address = this[UserEntity.address],
        phone = this[UserEntity.phone]
    )
}