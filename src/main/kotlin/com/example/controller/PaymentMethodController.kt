package com.example.controller

import com.example.contract.PaymentMethodContract
import com.example.data.model.PaymentMethodModel

class PaymentMethodController(
    private val service: PaymentMethodContract
) {

    fun getListPaymentMethods(): List<PaymentMethodModel> = service.getListPaymentMethods()

}