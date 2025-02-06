package com.pepinho.pmdm.exames

data class CalculatorUIState(
    val calorias: Int = 0,
    val tazas: Int = 1,
    val mujer: Boolean = true,
    val result: Int = 0,
    val porcentaje: Double = 0.0
)