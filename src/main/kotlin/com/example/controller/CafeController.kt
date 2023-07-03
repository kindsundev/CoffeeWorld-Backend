package com.example.controller

import com.example.data.model.CafeModel
import com.example.repository.CafeRepository

class CafeController(
    private val cafeRepository: CafeRepository
) {

    fun getListCafes(): List<CafeModel> = cafeRepository.getListCafes()

    fun getCafe(id: Int): CafeModel? = cafeRepository.getCafe(id)

}