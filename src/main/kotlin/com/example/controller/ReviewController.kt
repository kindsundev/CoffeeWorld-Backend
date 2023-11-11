package com.example.controller

import com.example.contract.ReviewContract
import com.example.data.dto.ReviewDTO
import com.example.data.model.ReviewModel

class ReviewController(
    private val service: ReviewContract
)  {

    fun getReview(id: Int): ReviewModel? = service.getReview(id)

    fun getReviewByDrinkId(drinkId: Int): List<ReviewModel> = service.getReviewByDrinkId(drinkId)

    fun createReview(review: ReviewDTO): Unit = this.service.createReview(review)

    fun updateReview(id: Int, review: ReviewDTO): Boolean = this.service.updateReview(id, review)

    fun deleteReview(id: Int): Boolean = service.deleteReview(id)

}