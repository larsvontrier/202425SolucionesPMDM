package com.pepinho.nba

object EquipoIconUtil {
    private val equipoIconMap = mapOf(
        "atl" to R.drawable.atl,
        "bos" to R.drawable.bos,
        "bkn" to R.drawable.bkn,
        "cha" to R.drawable.cha,
        "chi" to R.drawable.chi,
        "cle" to R.drawable.cle,
        "dal" to R.drawable.dal,
        "den" to R.drawable.den,
        "det" to R.drawable.det,
        "gsw" to R.drawable.gsw,
        "hou" to R.drawable.hou,
        "ind" to R.drawable.ind,
        "lac" to R.drawable.lac,
        "lal" to R.drawable.lal,
        "mem" to R.drawable.mem,
        "mia" to R.drawable.mia,
        "mil" to R.drawable.mil,
        "min" to R.drawable.min,
        "nop" to R.drawable.nop,
        "nyk" to R.drawable.nyk,
        "okc" to R.drawable.okc,
        "orl" to R.drawable.orl,
        "phi" to R.drawable.phi,
        "phx" to R.drawable.phx,
        "por" to R.drawable.por,
        "sac" to R.drawable.sac,
        "sas" to R.drawable.sas,
        "tor" to R.drawable.tor,
        "uta" to R.drawable.uta,
        "was" to R.drawable.was
    )

    fun getIconResource(abreviatura: String): Int {
        return equipoIconMap[abreviatura.lowercase()] ?: R.drawable.nba
    }
}