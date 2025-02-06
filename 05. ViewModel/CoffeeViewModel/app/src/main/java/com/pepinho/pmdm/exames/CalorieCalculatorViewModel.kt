package com.pepinho.pmdm.exames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalorieCalculatorViewModel: ViewModel() {

    private var _calculadoraState: MutableLiveData<CalculatorUIState> = MutableLiveData(
        CalculatorUIState()
    )
    val calculatorUIState: LiveData<CalculatorUIState>
        get() = _calculadoraState


    fun calcularCalorias(calorias: Int = 0, tazas: Int = 1,
                                mujer: Boolean = true){
        val total = tazas * calorias
        val totalDiario = if(mujer)  1800 else 2200

        _calculadoraState.value = CalculatorUIState(calorias, tazas, mujer,
            total, total.toDouble() / totalDiario * 100)

    }

}