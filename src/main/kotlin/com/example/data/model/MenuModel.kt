package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MenuModel(
    val category: CategoryModel,
    val drinkList: List<DrinksModel>
)