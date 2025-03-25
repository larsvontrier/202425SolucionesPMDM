package com.pepinho.freetogame.model

import androidx.room.Embedded
import androidx.room.Relation

data class JuegoConDetalles(
    @Embedded val juego: Juego,
    @Relation(
        parentColumn = "idGenero",
        entityColumn = "idGenero"
    )
    val genero: Genero,
    @Relation(
        parentColumn = "idPlataforma",
        entityColumn = "idPlataforma"
    )
    val plataforma: Plataforma,
    @Relation(
        parentColumn = "idJuego",
        entityColumn = "idJuego"
    )
    val requisitosSistema: RequisitosSistema?,
    @Relation(
        parentColumn = "idJuego",
        entityColumn = "idJuego"
    )
    val imagenes: List<Imagen>
)