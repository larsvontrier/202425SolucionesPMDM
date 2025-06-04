package com.pepinho.pmdm.exames.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pepinho.pmdm.exames.model.Cafe
import com.pepinho.pmdm.exames.model.Categoria
import com.pepinho.pmdm.exames.dao.CafeDao
import com.pepinho.pmdm.exames.dao.CategoriaDao

@Database(entities = [Cafe::class, Categoria::class], version = 1)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun cafeDao(): CafeDao
    abstract fun categoriaDao(): CategoriaDao
}