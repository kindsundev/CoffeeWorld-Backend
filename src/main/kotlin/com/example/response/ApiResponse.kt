package com.example.response

import kotlinx.serialization.Serializable

@Serializable
sealed class ApiResponse<T> {

    @Serializable
    data class Success<T>(val status: Boolean,val data: T) : ApiResponse<T>()

    @Serializable
    data class Error(val status: Boolean,val message: String) : ApiResponse<Nothing>()

}