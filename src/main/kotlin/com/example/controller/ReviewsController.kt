package com.example.controller

import com.example.contract.ReviewsContract
import com.example.data.dto.ReviewsDTO
import com.example.data.model.ReviewsModel
import com.example.repository.ReviewsRepository

class ReviewsController(
    private val repository: ReviewsRepository
) : ReviewsContract {

    override fun getReviews(id: Int): ReviewsModel? = repository.getReviews(id)

    override fun getReviewsByDrinksId(drinksId: Int): List<ReviewsModel> = repository.getReviewsByDrinksId(drinksId)

    override fun createReviews(reviews: ReviewsDTO): Unit = repository.createReviews(reviews)

    override fun updateReviews(id: Int, reviews: ReviewsDTO): Boolean = repository.updateReviews(id, reviews)

    override fun deleteReviews(id: Int): Boolean = repository.deleteReviews(id)
}