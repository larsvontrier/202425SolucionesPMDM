package com.pepinho.nba.model.repositorios

import com.pepinho.nba.model.Equipo

interface EquipoRepository {

    val equipos: List<Equipo>

    fun getEquipoById(idEquipo: Int) : Equipo

}