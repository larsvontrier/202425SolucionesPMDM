package com.pepinho.pmdm.applistas.model

import java.time.LocalDateTime


data class Mensaje(
    val id: Long,
    val contenido: String,
    val fecha: LocalDateTime
)