package com.pepinho.pmdm.exames.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cafe(
    @PrimaryKey(autoGenerate = true) val idCafe: Int = 0,
    val idCategoria: Int,
    val nombre: String,
    val tipo: String,
    val calorias: Int,
    val descripcion: String?,
    val foto: ByteArray?
) {
    override fun toString(): String {
        return "Cafe(idCafe=$idCafe, idCategoria=$idCategoria, nombre='$nombre', tipo='$tipo', calorias=$calorias, descripcion=$descripcion, foto=${foto?.contentToString()})"
    }
}
