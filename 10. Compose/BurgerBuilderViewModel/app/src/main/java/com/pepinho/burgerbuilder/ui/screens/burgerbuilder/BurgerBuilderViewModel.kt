package com.pepinho.burgerbuilder.ui.screens.burgerbuilder

import androidx.lifecycle.ViewModel
import com.pepinho.burgerbuilder.model.Ingrediente
import com.pepinho.burgerbuilder.model.Posicion
import com.pepinho.pmdm.burgerbuilder.model.Hamburguesa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BurgerBuilderViewModel : ViewModel() {
    private val _burger = MutableStateFlow(Hamburguesa(nome = "Hamburguesa personalizada"))
    val burger: StateFlow<Hamburguesa> = _burger.asStateFlow()

    // Lista de ingredientes disponible para la UI
    val ingredientes: List<Ingrediente> = Ingrediente.entries.toList()

    // Cambio el nombre del método a toggleIngrediente para que sea más claro con el propósito
    // y no se confunda con el método de la clase Hamburguesa
    fun toggleIngrediente(ingrediente: Ingrediente) {
        _burger.update { currentBurger ->
            val isOnBurger = currentBurger.ingredientes[ingrediente] != null
            currentBurger.conIngrediente(
                ingrediente = ingrediente,
                posicion = if (isOnBurger) {
                    null
                } else {
                    Posicion.ENRIBA
                }
            )
        }
    }

    fun resetBurger() {
        _burger.value = Hamburguesa(nome = "Hamburguesa personalizada")
    }
}