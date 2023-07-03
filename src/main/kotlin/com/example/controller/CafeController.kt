package com.example.controller

import com.example.contract.CafeContract
import com.example.data.model.CafeModel
import com.example.repository.CafeRepository

class CafeController(
    private val repository: CafeRepository
) : CafeContract {

    override fun getListCafes(): List<CafeModel> = repository.getListCafes()

    override fun getCafe(id: Int): CafeModel? = repository.getCafe(id)

}