package com.pepinho.pmdm.burgerbuilder.model

import android.os.Parcel
import android.os.Parcelable
import com.pepinho.pmdm.burgerbuilder.model.Recheo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Burger(
    val recheos: Map<Recheo, PosicionRecheo> = emptyMap()
) : Parcelable {
    val prezo: Double
        get() = 19.99 + recheos.asSequence() // precio base más el precio de los recheos
            .sumOf { (_, posicionRecheo) ->
                when (posicionRecheo) {
                    PosicionRecheo.Enriba,
                    PosicionRecheo.Abaixo -> 0.5
                    else -> 1.0
                }
            }

    fun conRecheo(recheo: Recheo, posicion: PosicionRecheo?): Burger {
        return copy(
            recheos = if (posicion == null) {
                recheos - recheo
            } else {
                recheos + (recheo to posicion)
            }
        )
    }

}
