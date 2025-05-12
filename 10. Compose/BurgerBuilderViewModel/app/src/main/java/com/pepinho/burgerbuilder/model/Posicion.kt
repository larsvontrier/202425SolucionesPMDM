package com.pepinho.burgerbuilder.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import com.pepinho.burgerbuilder.R

@Parcelize
enum class Posicion(
    @StringRes val nome: Int,
    @StringRes val descricion: Int
) : Parcelable {
    NON(R.string.posicion_non, R.string.posicion_non),
    ENRIBA(R.string.posicion_enriba, R.string.posicion_non),
    ABAIXO(R.string.posicion_abaixo, R.string.posicion_non),
    AMBOS(R.string.posición_ambos, R.string.posicion_non);

    companion object {
        fun String.toPosicion(): Posicion = when (this) {
            "ENRIBA" -> ENRIBA
            "ABAIXO" -> ABAIXO
            "AMBOS" -> AMBOS
            else -> NON
        }
    }
}