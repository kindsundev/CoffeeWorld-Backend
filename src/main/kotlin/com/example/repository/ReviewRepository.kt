package com.example.repository

import com.example.contract.ReviewContract
import com.example.data.dto.ReviewDTO
import com.example.data.entity.ReviewEntity
import com.example.data.entity.UserEntity
import com.example.data.model.ReviewModel
import com.example.util.toReviewModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class ReviewRepository(
    private val database: Database
): ReviewContract {

    override fun getReviewByDrinkId (drinkId: Int): List<ReviewModel> {
        val result = database.from(ReviewEntity)
            .innerJoin(UserEntity, on = ReviewEntity.userId eq UserEntity.id)
            .select()
            .where { ReviewEntity.drinkId eq drinkId }
            .map {
                ReviewModel(
                    id = it[ReviewEntity.id]!!,
                    drinkId = it[ReviewEntity.drinkId]!!,
                    userId = it[ReviewEntity.userId]!!,
                    rating = it[ReviewEntity.rating]!!,
                    comment = it[ReviewEntity.comment],
                    userName = it[UserEntity.name]
                )
            }
        return result
    }

    override fun getReview(id: Int): ReviewModel? {
        return database.from(ReviewEntity)
            .select()
            .where(ReviewEntity.id eq id)
            .map { it.toReviewModel() }
            .firstOrNull()
    }

    override fun createReview(review : ReviewDTO) {
        database.insert(ReviewEntity) {
            set(ReviewEntity.drinkId, review.drinkId)
            set(ReviewEntity.userId, review.userId)
            set(ReviewEntity.rating, review.rating)
            set(ReviewEntity.comment, review.comment)
        }
    }

    override fun updateReview(id: Int, review : ReviewDTO): Boolean {
        val updateRow = database.update(ReviewEntity) {
            set(ReviewEntity.rating, review.rating)
            set(ReviewEntity.comment, review.comment)
            where { it.id eq id }
        }
        return updateRow > 0
    }

    override fun deleteReview(id: Int): Boolean {
        val deleteRow = database.delete(ReviewEntity) {
            it.id eq id
        }
        return deleteRow > 0
    }
}