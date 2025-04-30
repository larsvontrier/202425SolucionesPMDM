package com.pepinho.pmdm.burgerbuilder.model
import androidx.annotation.StringRes
import com.pepinho.pmdm.burgerbuilder.R

enum class PosicionRecheo(
    @StringRes val label: Int
) {
    Enriba(R.string.posicion_enriba),
    Abaixo(R.string.posicion_abaixo),
    EnribaEAbaixo(R.string.posición_all)
}