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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class EquipoJsonRepository private constructor(context: Context) : EquipoRepository {

    private val _equipos = MutableStateFlow<List<Equipo>>(mutableListOf())
    override val equipos: StateFlow<List<Equipo>> get() = _equipos

    init {
        // Cargamos los equipos en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            loadEquiposFromJson(context)
        }
    }


    private suspend fun loadEquiposFromJson(context: Context)   {
        try {
            delay(3000) // Simulamos una carga de 2 segundos
            val tipo = object : TypeToken<List<Equipo>>() {}.type
            val gson: Gson =
                GsonBuilder().registerTypeAdapter(tipo, EquiposJsonTypeAdapter()).create()

            BufferedReader(InputStreamReader(context.assets.open("equipos.json"))).use { reader ->
                val equiposList = gson.fromJson<List<Equipo>>(reader, tipo)

//                // añado elemento a elemento a la lista de equipos:
//
//                equiposList?.forEach {
//                    _equipos.value = _equipos.value + it
//                    delay(500)
//                }

                _equipos.value = equiposList ?: emptyList()
            }
        } catch (e: Exception) {
            Log.e("EquipoJsonRepository", "Error al cargar los equipos de JSON: ${e.message}")

            _equipos.value = emptyList() // En caso de error, devolvemos una lista vacía
        }
    }

    override fun getEquipoById(idEquipo: Int): Equipo? {
        return _equipos.value.firstOrNull { it.idEquipo == idEquipo }
    }

    companion object {
        @Volatile
        private var INSTANCE: EquipoJsonRepository? = null

        fun getInstance(context: Context): EquipoJsonRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: EquipoJsonRepository(context.applicationContext).also { INSTANCE = it }
            }
        }
    }


}