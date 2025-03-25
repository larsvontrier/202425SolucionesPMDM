package com.pepinho.freetogame.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.pepinho.freetogame.model.Imagen
import com.pepinho.freetogame.model.Juego
import com.pepinho.freetogame.model.JuegoConDetalles
import kotlinx.coroutines.flow.Flow

@Dao
interface JuegoDao {

    @Query("SELECT * FROM Juego")
    fun getAll(): Flow<List<Juego>> // Usamos Flow para observar cambios

    @Query("SELECT * FROM Juego WHERE idJuego = :idJuego")
    fun getByIdJuego(idJuego: Int): Flow<Juego> // Usamos Flow para observar cambios

    @Query("SELECT * FROM Juego WHERE idPlataforma = :idPlataforma")
    fun getAllByIdPlataforma(idPlataforma: Int): Flow<List<Juego>> // Usamos Flow

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(juego: Juego)

    @Update
    suspend fun update(juego: Juego)

    @Delete
    suspend fun delete(juego: Juego)

    @Transaction
    @Query("SELECT * FROM Juego WHERE idJuego = :idJuego")
    fun getJuegoConDetallesByIdJuego(idJuego: Int): Flow<JuegoConDetalles?> // Usamos Flow

    @Transaction
    @Query("SELECT * FROM Juego")
    fun getAllJuegosConDetalles(): Flow<List<JuegoConDetalles>> // Usamos Flow

    @Query("SELECT * FROM Imagen WHERE idJuego = :idJuego")
    fun getImagenesByIdJuego(idJuego: Int): Flow<List<Imagen>> // Usamos Flow


}