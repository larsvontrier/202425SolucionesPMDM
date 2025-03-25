package com.pepinho.freetogame.database

import androidx.room.Dao
import androidx.room.Query
import com.pepinho.freetogame.model.Genero
import kotlinx.coroutines.flow.Flow

@Dao
interface GeneroDao {
    @Query("SELECT * FROM Genero")
    fun getAll(): Flow<List<Genero>>

    @Query("SELECT * FROM Genero WHERE idGenero = :idGenero")
    suspend fun getByIdGenero(idGenero: Int): Genero
}