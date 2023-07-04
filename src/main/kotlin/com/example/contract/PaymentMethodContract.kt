package com.example.contract

import com.example.data.model.PaymentMethodModel

interface PaymentMethodContract {

    fun getListPaymentMethods(): List<PaymentMethodModel>

}