package com.pepinho.freetogame.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genero(
    @PrimaryKey val idGenero: Int,
    val nombre: String
)