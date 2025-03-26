package com.pepinho.freetogame.database

import androidx.room.Dao
import androidx.room.Query
import com.pepinho.freetogame.model.Imagen

@Dao
interface ImagenDao {

    @Query("SELECT * FROM Imagen")
    suspend fun getAll(): List<Imagen>

    @Query("SELECT * FROM Imagen WHERE idImagen = :idImagen")
    suspend fun getByIdImagen(idImagen: Int): Imagen

    @Query("SELECT * FROM Imagen WHERE idJuego = :idJuego")
    suspend fun getImagenesByIdJuego(idJuego: Int): List<Imagen>
}