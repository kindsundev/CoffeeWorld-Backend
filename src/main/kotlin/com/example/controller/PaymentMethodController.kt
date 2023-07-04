package com.example.controller

import com.example.contract.PaymentMethodContract
import com.example.data.model.PaymentMethodModel
import com.example.repository.PaymentMethodRepository

class PaymentMethodController(
    private val repository: PaymentMethodRepository
): PaymentMethodContract {

    override fun getListPaymentMethods(): List<PaymentMethodModel> = repository.getListPaymentMethods()

}