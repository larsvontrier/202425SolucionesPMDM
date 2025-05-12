package com.pepinho.pmdm.burgerbuilder.model

import android.os.Parcelable
import com.pepinho.burgerbuilder.model.Ingrediente
import com.pepinho.burgerbuilder.model.Posicion
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hamburguesa(
    val ingredientes: Map<Ingrediente, Posicion> = emptyMap(),
    val nome: String,
) : Parcelable {
    val prezo: Double
        get() = 5.99 + ingredientes.asSequence() // precio base más el precio de los recheos
            .sumOf { (_, posicionIngrediente) ->
                when (posicionIngrediente) {
                    Posicion.ENRIBA,
                    Posicion.ABAIXO -> 0.5
                    else -> 1.0
                }
            }

    fun conIngrediente(ingrediente: Ingrediente, posicion: Posicion?): Hamburguesa {
        return copy(
            ingredientes = if (posicion == null) {
                ingredientes - ingrediente
            } else {
                ingredientes + (ingrediente to posicion)
            }
        )
    }



}
