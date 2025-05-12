package com.pepinho.pmdm.burgerbuilder.model
import androidx.annotation.DrawableRes
import com.pepinho.pmdm.burgerbuilder.R

import androidx.annotation.StringRes

enum class Recheo (@StringRes val nome: Int){
    Leituga(nome = R.string.recheo_leituga),
    Tomate(nome = R.string.recheo_tomate),
    Ketchup(nome = R.string.recheo_ketchup),
    Maionesa(nome = R.string.recheo_maionesa),
    Mostaza(nome = R.string.recheo_mostaza),
    Queixo(nome = R.string.recheo_queixo),
    Cogombrinho(nome = R.string.recheo_cogombro),
    Toucinho(nome = R.string.recheo_toucinho    ),
    Cebola(nome = R.string.recheo_cebola),
}