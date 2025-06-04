package com.pepinho.pmdm.exames.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pepinho.pmdm.exames.model.Cafe
import com.pepinho.pmdm.exames.model.Categoria
import com.pepinho.pmdm.exames.model.CategoriaCafe

@Dao
interface CategoriaDao {

    @Insert
    suspend fun insert(categoria: Categoria)

    @Query("SELECT * FROM Categoria")
    suspend fun getAll(): List<Categoria>

    @Query("SELECT * FROM Categoria WHERE idCategoria = :idCategoria")
    suspend fun getCategoriaById(idCategoria: Int): Categoria

    //
    @Query("SELECT * FROM Categoria ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): Categoria

    @Transaction
    @Query("Select * from Categoria")
    suspend fun getCategoriaCafes(): List<CategoriaCafe>

}