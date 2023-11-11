package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MenuModel(
    val cafeId: Int,
    val beverageCategory: List<BeverageCategory>
)

@Serializable
data class BeverageCategory(
    val cafeId: Int,
    val category: CategoryModel,
    val drinkList: List<DrinksModel>
)