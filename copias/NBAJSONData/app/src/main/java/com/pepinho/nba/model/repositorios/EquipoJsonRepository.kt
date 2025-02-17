package com.pepinho.nba.model.repositorios

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.pepinho.nba.model.Equipo
import com.pepinho.nba.model.gson.EquiposJsonTypeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class EquipoJsonRepository private constructor(context: Context) : EquipoRepository {

    private val _equipos = mutableListOf<Equipo>()
    override val equipos: List<Equipo>
        get() = _equipos

    init {
        // Los cargamos sólo la primera vez
        CoroutineScope(Dispatchers.IO).launch {
            getEquiposFromJson(context)
        }
    }

    private suspend fun getEquiposFromJson(context: Context) {
        try {
            // Tipo de datos de la lista de equipos:
            val tipo = object : TypeToken<List<Equipo>>() {}.type
            val gson: Gson = GsonBuilder()
                .registerTypeAdapter(
                    tipo,
                    EquiposJsonTypeAdapter() // Podríamos registrar el EquiposJsonDeserializer o el TypeAdapter
                )
                .create()
            // Abrimos un BufferedReader para leer el archivo JSON.
            // La función use() cierra el recurso automáticamente al finalizar.
            BufferedReader(InputStreamReader(context.assets.open("equipos.json"))).use { reader ->
                _equipos.addAll(gson.fromJson(reader, tipo))
            }

        } catch (e: Exception) {
            Log.e("EquipoJsonRepository", "Error al cargar los equipos de JSON: ${e.message}")
        }
    }

    override fun getEquipoById(idEquipo: Int): Equipo {
        return _equipos.firstOrNull() { it.idEquipo == idEquipo } ?: Equipo()
    }

//    override fun getEquipos(): List<Equipo> {
//        return equipos
//    }

    companion object {
        private var INSTANCE: EquipoJsonRepository? = null

        @Synchronized
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = EquipoJsonRepository(context)
            }
        }

        fun getInstance(): EquipoJsonRepository {
            return INSTANCE
                ?: throw IllegalArgumentException("Debe inicializar EquipoJsonRepository")
        }
    }


}