package com.example.controller

import com.example.contract.BillContract
import com.example.data.dto.BillDTO
import com.example.data.model.BillModel

class BillController(
    private val bill : BillContract
) {

    fun getBillByUserId(userId: Int): List<BillModel> = bill.getBillByUserId(userId)

    fun createBill(bill: BillDTO): BillModel? = this.bill.createBill(bill)

}