package com.pepinho.burgerbuilder.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import com.pepinho.burgerbuilder.R

@Parcelize
enum class Ingrediente(
    @StringRes val nome: Int,
    @StringRes val descricion: Int,
) : Parcelable {
    LETUGA(R.string.recheo_leituga, R.string.recheo_leituga),
    TOMATE(R.string.recheo_tomate, R.string.recheo_tomate),
    KETCHUP(R.string.recheo_ketchup, R.string.recheo_ketchup),
    MAIONESA(R.string.recheo_maionesa, R.string.recheo_maionesa),
    MOSTAZA(R.string.recheo_mostaza, R.string.recheo_mostaza),
    QUEIXO(R.string.recheo_queixo, R.string.recheo_queixo),
    COGOMBRO(R.string.recheo_cogombro, R.string.recheo_cogombro),
    TOUCINHO(R.string.recheo_toucinho, R.string.recheo_toucinho),
    CEBOLA(R.string.recheo_cebola, R.string.recheo_cebola)
}