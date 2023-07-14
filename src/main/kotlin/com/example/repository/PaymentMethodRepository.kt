package com.example.repository

import com.example.contract.PaymentMethodContract
import com.example.data.entity.PaymentMethodEntity
import com.example.data.model.PaymentMethodModel
import com.example.util.toPaymentMethodModel
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select

class PaymentMethodRepository(
    private val database: Database
): PaymentMethodContract {

    override fun getListPaymentMethods(): List<PaymentMethodModel> {
        return database.from(PaymentMethodEntity)
            .select()
            .map {
                it.toPaymentMethodModel()
            }
    }

}