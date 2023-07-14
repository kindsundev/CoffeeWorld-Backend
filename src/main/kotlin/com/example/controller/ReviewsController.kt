package com.example.controller

import com.example.contract.ReviewsContract
import com.example.data.dto.ReviewsDTO
import com.example.data.model.ReviewsModel

class ReviewsController(
    private val reviews: ReviewsContract
)  {

    fun getReviews(id: Int): ReviewsModel? = reviews.getReviews(id)

    fun getReviewsByDrinksId(drinksId: Int): List<ReviewsModel> = reviews.getReviewsByDrinksId(drinksId)

    fun createReviews(reviews: ReviewsDTO): Unit = this.reviews.createReviews(reviews)

    fun updateReviews(id: Int, reviews: ReviewsDTO): Boolean = this.reviews.updateReviews(id, reviews)

    fun deleteReviews(id: Int): Boolean = reviews.deleteReviews(id)

}