package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BeverageCategoryModel(
    val categoryName: String,
    val drinkList: List<DrinksModel>
)