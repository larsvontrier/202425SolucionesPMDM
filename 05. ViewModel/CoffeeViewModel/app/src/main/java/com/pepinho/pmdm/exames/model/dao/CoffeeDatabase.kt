package com.pepinho.pmdm.exames.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pepinho.pmdm.exames.model.Cafe
import com.pepinho.pmdm.exames.model.Categoria

@Database(entities = [Cafe::class, Categoria::class], version = 1)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun cafeDao(): CafeDao
    abstract fun categoriaDao(): CategoriaDao
}