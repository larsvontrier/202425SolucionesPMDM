package com.pepinho.pmdm.nba.repositorios

import com.pepinho.pmdm.nba.model.Equipo

interface EquipoRepository {

    val equipos: List<Equipo>

    fun getEquipoById(idEquipo: Int) : Equipo

}