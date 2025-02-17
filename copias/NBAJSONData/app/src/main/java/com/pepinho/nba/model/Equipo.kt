package com.pepinho.nba.model


import java.time.LocalDateTime

data class Equipo (
    var idEquipo: Int = 0,
    var abreviatura: String = "",
    var nombre: String = "",
    var nombreCompleto: String = "",
    var ciudad: String = "",
    var division: Division = Division.PACIFIC,
    var conferencia: Conferencia = Conferencia.WEST,
    val fecha: LocalDateTime? = null,
    val isFavorito: Boolean = false,
    var photo: ByteArray? = null
)
