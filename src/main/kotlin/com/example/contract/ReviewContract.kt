package com.example.contract

import com.example.data.dto.ReviewDTO
import com.example.data.model.ReviewModel

interface ReviewContract {

    fun getReviewByDrinkId(drinkId: Int): List<ReviewModel>

    fun getReview(id: Int): ReviewModel?

    fun createReview(review: ReviewDTO)

    fun updateReview(id: Int, review: ReviewDTO): Boolean

    fun deleteReview(id: Int): Boolean

}