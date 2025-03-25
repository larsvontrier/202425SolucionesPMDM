package com.pepinho.freetogame.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plataforma (
    @PrimaryKey val idPlataforma: Int,
    val nombre: String
)