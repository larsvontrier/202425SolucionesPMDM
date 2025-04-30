package com.pepinho.freetogame.database

import androidx.room.Dao
import androidx.room.Query
import com.pepinho.freetogame.model.Juego
import com.pepinho.freetogame.model.RequisitosSistema
import kotlinx.coroutines.flow.Flow

@Dao
interface RequisitosSistemaDao {

    @Query("SELECT * FROM RequisitosSistema WHERE idJuego = :idJuego")
    fun getRequisitosByJuegoIdJuego(idJuego: Int): Flow<RequisitosSistema?>

    @Query("SELECT * FROM RequisitosSistema")
    suspend fun getRequisitosSistema(): List<RequisitosSistema>

}