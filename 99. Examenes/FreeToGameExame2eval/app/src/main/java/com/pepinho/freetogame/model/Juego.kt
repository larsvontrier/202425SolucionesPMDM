package com.pepinho.freetogame.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "Juego",
    foreignKeys = [
        ForeignKey(
            entity = Genero::class,  // Tabla referenciada
            parentColumns = ["idGenero"],  // Columna en la tabla referenciada
            childColumns = ["idGenero"],  // Columna en esta tabla
            onDelete = ForeignKey.NO_ACTION,  // Acción al eliminar
            onUpdate = ForeignKey.NO_ACTION   // Acción al actualizar
        ),
        ForeignKey(
            entity = Plataforma::class,  // Tabla referenciada
            parentColumns = ["idPlataforma"],  // Columna en la tabla referenciada
            childColumns = ["idPlataforma"],  // Columna en esta tabla
            onDelete = ForeignKey.NO_ACTION,  // Acción al eliminar
            onUpdate = ForeignKey.NO_ACTION   // Acción al actualizar
        )
    ]
)
data class Juego (
    @PrimaryKey(autoGenerate = true) val idJuego: Int = 0,
    val fecha: LocalDate,
    val idGenero: Int,
    val idPlataforma: Int,
    val desarrollador: String,
    val editor: String,
    val estado: String,
    val miniatura: String,
    val url: String,
    val descripcionCorta: String,
    val descripcion: String,
    val titulo: String
)