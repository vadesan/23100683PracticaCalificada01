package com.vadesan.practica01dsm.presentation.data.model

import java.time.LocalDateTime

data class OperationModel(
    val originCurrency: String = "",
    val destinyCurrency: String = "",
    val amount: Double = 0.0,
    val result: Double = 0.0,
    val date: LocalDateTime? = null
)
