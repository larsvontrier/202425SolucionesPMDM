package com.pepinho.pmdm.exames.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pepinho.pmdm.exames.model.Cafe
import com.pepinho.pmdm.exames.model.CafeConCategoria

@Dao
interface CafeDao {

    @Insert
    suspend fun insert(cafe: Cafe)

    @Query("SELECT * FROM Cafe")
    suspend fun getAll(): List<Cafe>

    @Query("SELECT * FROM Cafe WHERE idCafe = :idCafe")
    suspend fun getCafeById(idCafe: Int): Cafe


//    @Query("SELECT * FROM Usuario WHERE idUsuario IN (:idsUsuario)")
//    suspend fun getAllByIds(idsUsuario: IntArray): List<Usuario>

    @Query("SELECT * FROM Cafe WHERE nombre LIKE :nombre LIMIT 1")
    suspend fun findByNome(nombre: String): Cafe

    @Insert
    suspend fun insertAll(vararg cafes: Cafe)

    @Delete
    suspend fun delete(cafe: Cafe)

    @Query("SELECT * FROM Cafe ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): Cafe

    @Transaction
    @Query("SELECT * FROM Cafe")
    suspend fun getCafesConCategoria(): List<CafeConCategoria>

    @Transaction
    @Query("SELECT * FROM Cafe WHERE idCafe = :idCafe")
    suspend fun getCafeConCategoriaById(idCafe: Int): CafeConCategoria

    @Transaction
    @Query("SELECT * FROM Cafe ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCafeConCategoria(): CafeConCategoria
}