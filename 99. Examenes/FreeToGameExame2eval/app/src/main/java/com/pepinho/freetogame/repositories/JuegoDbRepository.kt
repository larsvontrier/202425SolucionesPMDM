package com.pepinho.freetogame.repositories

import android.content.Context
import androidx.room.Room
import com.pepinho.freetogame.database.GameDatabase
import com.pepinho.freetogame.model.JuegoConDetalles
import com.pepinho.freetogame.model.Genero
import com.pepinho.freetogame.model.Imagen
import com.pepinho.freetogame.model.Juego
import com.pepinho.freetogame.model.RequisitosSistema
import kotlinx.coroutines.flow.Flow

private const val DATABASE_NAME = "FreeToGame.sqlite"

class JuegoDbRepository private constructor(context: Context) : JuegoRepository {
    private val database: GameDatabase = Room.databaseBuilder(
        context.applicationContext,
        GameDatabase::class.java,
        DATABASE_NAME
    ).createFromAsset(DATABASE_NAME).build()

    // Exponemos Flow para observar cambios en tiempo real
    override fun getAll(): Flow<List<Juego>> {
        return database.juegoDao().getAll()
    }

    override fun getAllByIdPlataforma(idPlataforma: Int): Flow<List<Juego>> {
        return database.juegoDao().getAllByIdPlataforma(idPlataforma)
    }

    override fun getByIdJuego(idJuego: Int): Flow<Juego> {
        return database.juegoDao().getByIdJuego(idJuego)
    }

    override suspend fun insert(juego: Juego) {
        database.juegoDao().insert(juego)
    }

    override suspend fun update(juego: Juego) {
        database.juegoDao().update(juego)
    }

    override suspend fun delete(juego: Juego) {
        database.juegoDao().delete(juego)
    }

    override fun getJuegoConDetallesByIdJuego(idJuego: Int): Flow<JuegoConDetalles?> {
        return database.juegoDao().getJuegoConDetallesByIdJuego(idJuego)
    }

    override fun getAllJuegosConDetalles(): Flow<List<JuegoConDetalles>> {
        return database.juegoDao().getAllJuegosConDetalles()
    }

    override fun getImagenesByIdJuego(idJuego: Int): Flow<List<Imagen>> {
        return database.juegoDao().getImagenesByIdJuego(idJuego)
    }

    override fun getRequisitosByJuegoIdJuego(idJuego: Int): Flow<RequisitosSistema?> {
        return database.requisitosSistemaDao().getRequisitosByJuegoIdJuego(idJuego)
    }

    override fun getAllGeneros(): Flow<List<Genero>> {
        return database.generoDao().getAll()
    }

    companion object {
        @Volatile
        private var INSTANCE: JuegoDbRepository? = null

        fun getInstance(context: Context): JuegoDbRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: JuegoDbRepository(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}