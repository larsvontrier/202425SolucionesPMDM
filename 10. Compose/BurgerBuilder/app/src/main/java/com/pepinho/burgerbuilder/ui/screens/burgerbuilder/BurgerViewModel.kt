package com.pepinho.burgerbuilder.ui.screens.burgerbuilder

import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import com.pepinho.burgerbuilder.model.Ingrediente
import com.pepinho.burgerbuilder.model.Posicion
import com.pepinho.pmdm.burgerbuilder.model.Hamburguesa
import kotlinx.coroutines.flow.asStateFlow

class BurgerViewModel(): ViewModel() {

    private val _burgerState = MutableStateFlow(Hamburguesa(nome = "Hamburguesa personalizada"))
    val burgerState = _burgerState.asStateFlow()


    public fun conIngrediente(ingrediente: Ingrediente): Hamburguesa{
        val burger = _burgerState.value
        return burger.conIngrediente(
            ingrediente = ingrediente,
            posicion = if(burger.ingredientes[ingrediente] != null) {
                null
            } else {
                Posicion.ENRIBA
            }
        )
    }

    fun getPrezo(): Double = _burgerState.value.prezo

}