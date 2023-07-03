package com.example.controller

import com.example.data.dto.ReviewsDTO
import com.example.data.model.ReviewsModel
import com.example.repository.ReviewsRepository

class ReviewsController(
    private val reviewsRepository: ReviewsRepository
) {

    fun getListReviews(): List<ReviewsModel> = reviewsRepository.getListReviews()

    fun getReviews(id: Int): ReviewsModel? = reviewsRepository.getReviews(id)

    fun createReviews(reviews : ReviewsDTO): Unit = reviewsRepository.createReviews(reviews)

    fun updateReviews(id: Int, reviews : ReviewsDTO): Boolean = reviewsRepository.updateReviews(id, reviews)

    fun deleteReviews(id: Int): Boolean = reviewsRepository.deleteReviews(id)
}