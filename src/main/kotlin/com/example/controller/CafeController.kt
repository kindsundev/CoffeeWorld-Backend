package com.example.controller

import com.example.contract.CafeContract
import com.example.data.model.CafeModel

class CafeController(
    private val cafe: CafeContract
) {
     fun getListCafes(): List<CafeModel> = cafe.getListCafes()

    fun getCafe(id: Int): CafeModel? = cafe.getCafe(id)

}