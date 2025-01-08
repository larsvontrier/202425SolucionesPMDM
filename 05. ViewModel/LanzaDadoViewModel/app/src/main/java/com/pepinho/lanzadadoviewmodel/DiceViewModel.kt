package com.pepinho.lanzadadoviewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.lanzadadoviewmodel.model.DiceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DiceViewModel : ViewModel() {
    // Como norma general, el estado del ViewModel debe ser privado para que no se puedan acceder directamente desde la IU y el flujo sea unidireccional
    private val _diceState = MutableStateFlow(DiceState())
    // Se expone un StateFlow para que la IU pueda recolectar el estado de la UI, pero de sólo lectura
    val diceState: StateFlow<DiceState> = _diceState

    // Método para lanzar el dado, se invoca desde la IU
    fun rollDice() {
        viewModelScope.launch {
            // MutableStateFlow es mutable, por lo que se puede modificar su valor, que se guarda en _diceState y se emite a la IU cuando cambia
            _diceState.value = DiceState(isRolling = true)
            // Simulamos un retraso de 1 segundo antes de mostrar el resultado del dado
            delay(1000)
            // Se actualiza el estado de la UI con el valor del dado y se indica que ya no se está rodando
            _diceState.value = DiceState(value = (1..6).random(), isRolling = false)
        }
    }
}