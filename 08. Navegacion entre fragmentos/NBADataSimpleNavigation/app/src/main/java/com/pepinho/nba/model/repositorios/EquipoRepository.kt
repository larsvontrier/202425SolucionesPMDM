package com.pepinho.nba.model.repositorios

import com.pepinho.nba.model.Equipo
import kotlinx.coroutines.flow.StateFlow

interface EquipoRepository {

    val equipos: StateFlow<List<Equipo>>

    fun getEquipoById(idEquipo: Int) : Equipo?

}