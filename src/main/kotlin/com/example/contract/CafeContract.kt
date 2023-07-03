package com.example.contract

import com.example.data.model.CafeModel

interface CafeContract {
    fun getListCafes(): List<CafeModel>

    fun getCafe(id: Int): CafeModel?
}