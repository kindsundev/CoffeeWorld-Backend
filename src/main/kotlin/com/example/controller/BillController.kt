package com.example.controller

import com.example.contract.BillContract
import com.example.data.dto.BillDTO
import com.example.data.model.BillModel
import com.example.repository.BillRepository

class BillController(
    private val repository : BillRepository
) : BillContract {

    override fun getBillByUserId(userId: Int): List<BillModel> = repository.getBillByUserId(userId)

    override fun createBill(bill: BillDTO): BillModel? = repository.createBill(bill)

}