package com.example.repository

import com.example.data.db.DatabaseConnector
import com.example.data.entity.CafeEntity
import com.example.data.model.CafeModel
import com.example.util.toCafeModel
import org.ktorm.dsl.*

class CafeRepository(
    private val database: DatabaseConnector
) {

    fun getListCafes(): List<CafeModel> {
        val result = database.connect.from(CafeEntity)
            .select()
            .map {
                it.toCafeModel()
            }
        return result
    }

    fun getCafe(id: Int): CafeModel? {
        val result = database.connect.from(CafeEntity)
            .select()
            .where(CafeEntity.id eq id)
            .map {
                it.toCafeModel()
            }
        return result.firstOrNull()
    }

}