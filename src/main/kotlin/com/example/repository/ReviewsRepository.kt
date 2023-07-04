package com.example.repository

import com.example.data.dto.ReviewsDTO
import com.example.data.entity.ReviewsEntity
import com.example.data.entity.UserEntity
import com.example.data.model.ReviewsModel
import com.example.util.toReviewsModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class ReviewsRepository(
    private val database: Database
) {

    fun getReviewsByDrinksId (drinksId: Int): List<ReviewsModel> {
        val result = database.from(ReviewsEntity)
            .innerJoin(UserEntity, on = ReviewsEntity.userId eq UserEntity.id)
            .select()
            .where { ReviewsEntity.drinksId eq drinksId }
            .map {
                ReviewsModel(
                    id = it[ReviewsEntity.id]!!,
                    drinksId = it[ReviewsEntity.drinksId]!!,
                    userId = it[ReviewsEntity.userId]!!,
                    rating = it[ReviewsEntity.rating]!!,
                    comment = it[ReviewsEntity.comment],
                    userName = it[UserEntity.name]
                )
            }
        return result
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