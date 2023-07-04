package com.example.contract

import com.example.data.dto.ReviewsDTO
import com.example.data.model.ReviewsModel

interface ReviewsContract {

    fun getReviewsByDrinksId(drinksId: Int): List<ReviewsModel>

    fun getReviews(id: Int): ReviewsModel?

    fun createReviews(reviews: ReviewsDTO)

    fun updateReviews(id: Int, reviews: ReviewsDTO): Boolean

    fun deleteReviews(id: Int): Boolean

}