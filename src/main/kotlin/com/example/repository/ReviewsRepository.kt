package com.example.repository

import com.example.data.dto.ReviewsDTO
import com.example.data.entity.ReviewsEntity
import com.example.data.model.ReviewsModel
import com.example.util.toReviewsModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class ReviewsRepository(
    private val database: Database
) {

    fun getListReviews(): List<ReviewsModel> {
        return database.from(ReviewsEntity)
            .select()
            .map { it.toReviewsModel() }
    }

    fun getReviews(id: Int): ReviewsModel? {
        return database.from(ReviewsEntity)
            .select()
            .where(ReviewsEntity.id eq id)
            .map { it.toReviewsModel() }
            .firstOrNull()
    }

    fun createReviews(reviews : ReviewsDTO) {
        database.insert(ReviewsEntity) {
            set(ReviewsEntity.drinksId, reviews.drinksId)
            set(ReviewsEntity.userId, reviews.userId)
            set(ReviewsEntity.rating, reviews.rating)
            set(ReviewsEntity.comment, reviews.comment)
        }
    }

    fun updateReviews(id: Int, reviews : ReviewsDTO): Boolean {
        val updateRow = database.update(ReviewsEntity) {
            set(ReviewsEntity.rating, reviews.rating)
            set(ReviewsEntity.comment, reviews.comment)
            where { it.id eq id }
        }
        return updateRow > 0
    }

    fun deleteReviews(id: Int): Boolean {
        val deleteRow = database.delete(ReviewsEntity) {
            it.id eq id
        }
        return deleteRow > 0
    }
}