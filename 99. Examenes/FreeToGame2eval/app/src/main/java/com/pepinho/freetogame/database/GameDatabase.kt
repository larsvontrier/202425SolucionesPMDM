package com.pepinho.freetogame.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pepinho.freetogame.model.Genero
import com.pepinho.freetogame.model.Imagen
import com.pepinho.freetogame.model.Juego
import com.pepinho.freetogame.model.Plataforma
import com.pepinho.freetogame.model.RequisitosSistema

@Database(
    entities = [Genero::class, Plataforma::class, Juego::class, RequisitosSistema::class, Imagen::class],
    version = 1
)
@TypeConverters(GameTypeConverters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun generoDao(): GeneroDao
    abstract fun plataformaDao(): PlataformaDao
    abstract fun juegoDao(): JuegoDao
    abstract fun requisitosSistemaDao(): RequisitosSistemaDao
    abstract fun imagenDao(): ImagenDao
}