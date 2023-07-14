package com.example.controller

import com.example.contract.PaymentMethodContract
import com.example.data.model.PaymentMethodModel

class PaymentMethodController(
    private val paymentMethod: PaymentMethodContract
) {

    fun getListPaymentMethods(): List<PaymentMethodModel> = paymentMethod.getListPaymentMethods()

}