package com.example.response

import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResponse<T>(val success: Boolean) {

    @Serializable
    data class Success<T>(val data: T) : ApiResponse<T>(true)

    @Serializable
    data class Error(val message: String) : ApiResponse<Nothing>(false)

}