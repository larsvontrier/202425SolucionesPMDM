package com.pepinho.freetogame.repositories

import com.pepinho.freetogame.model.JuegoConDetalles
import com.pepinho.freetogame.model.Genero
import com.pepinho.freetogame.model.Imagen
import com.pepinho.freetogame.model.Juego
import com.pepinho.freetogame.model.RequisitosSistema
import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.StateFlow

interface JuegoRepository {
    fun getAll(): Flow<List<Juego>>
    fun getAllByIdPlataforma(idPlataforma: Int): Flow<List<Juego>>
    fun getByIdJuego(idJuego: Int): Flow<Juego>
    fun getJuegoConDetallesByIdJuego(idJuego: Int): Flow<JuegoConDetalles?>
    fun getAllJuegosConDetalles(): Flow<List<JuegoConDetalles>>
    fun getImagenesByIdJuego(idJuego: Int): Flow<List<Imagen>>
    fun getRequisitosByJuegoIdJuego(idJuego: Int): Flow<RequisitosSistema?>
    fun getAllGeneros(): Flow<List<Genero>>
    suspend fun insert(juego: Juego)
    suspend fun update(juego: Juego)
    suspend fun delete(juego: Juego)
}