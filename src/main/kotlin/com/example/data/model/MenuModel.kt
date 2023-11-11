package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuModel(
    @SerialName("cafe_id")
    val cafeId: Int,
    @SerialName("beverage_category")
    val beverageCategory: List<BeverageCategory>
)

@Serializable
data class BeverageCategory(
    @SerialName("cafe_id")
    val cafeId: Int,
    val category: CategoryModel,
    val drinks: List<DrinkModel>
)