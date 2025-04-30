package com.pepinho.freetogame.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Juego::class,  // Tabla referenciada
            parentColumns = ["idJuego"],  // Columna en la tabla referenciada
            childColumns = ["idJuego"],  // Columna en esta tabla
            onDelete = ForeignKey.NO_ACTION,  // Acción al eliminar
            onUpdate = ForeignKey.NO_ACTION   // Acción al actualizar
        )
    ]
)
data class RequisitosSistema(
    @PrimaryKey val idJuego: Int,
    val almacenamiento: String,
    val graficos: String,
    val memoria: String,
    val os: String,
    val procesador: String
)