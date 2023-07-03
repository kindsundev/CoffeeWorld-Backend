package com.example.repository

import com.example.data.entity.CafeEntity
import com.example.data.model.CafeModel
import com.example.util.toCafeModel
import org.ktorm.database.Database
import org.ktorm.dsl.*

class CafeRepository(
    private val database: Database
) {

    fun getListCafes(): List<CafeModel> {
        val result = database.from(CafeEntity)
            .select()
            .map {
                it.toCafeModel()
            }
        return result
    }

    fun getCafe(id: Int): CafeModel? {
        val result = database.from(CafeEntity)
            .select()
            .where(CafeEntity.id eq id)
            .map {
                it.toCafeModel()
            }
        return result.firstOrNull()
    }

}