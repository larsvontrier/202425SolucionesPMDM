package com.pepinho.freetogame.database

import androidx.room.Dao
import androidx.room.Query
import com.pepinho.freetogame.model.Plataforma

@Dao
interface PlataformaDao {

    @Query("SELECT * FROM plataforma")
    suspend fun getAll(): List<Plataforma>

    @Query("SELECT * FROM plataforma WHERE idPlataforma = :idPlataforma")
    suspend fun getByIdPlataforma(idPlataforma: Int): Plataforma
}