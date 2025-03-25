package com.pepinho.nba.gson

import com.pepinho.nba.model.Equipo


class Equipos(val equipos: MutableList<Equipo> = mutableListOf<Equipo>()) {
    fun getAsList() : List<Equipo> {
        return equipos
    }
}
