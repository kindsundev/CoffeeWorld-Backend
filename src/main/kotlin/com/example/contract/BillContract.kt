package com.example.contract

import com.example.data.dto.BillDTO
import com.example.data.model.BillModel

interface BillContract {

    fun getBillByUserId(userId: Int): List<BillModel>

    fun createBill(bill: BillDTO)

}