package com.pepinho.nba.model.repositorios

import android.content.Context
import com.pepinho.nba.model.Equipo
import com.pepinho.nba.retrofit.ClienteRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EquipoAPIRepository private constructor(context: Context) : EquipoRepository {
    private val _equipos = MutableStateFlow<List<Equipo>>(mutableListOf())
    override val equipos: StateFlow<List<Equipo>> get() = _equipos

    override fun getEquipoById(idEquipo: Int): Equipo? {
        return _equipos.value.firstOrNull { it.idEquipo == idEquipo }
    }

    init {
        // Cargamos los equipos en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            _equipos.value = ClienteRetrofit.apiEquipo.getEquipos().getAsList()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: EquipoAPIRepository? = null

        fun getInstance(context: Context): EquipoAPIRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: EquipoAPIRepository(context.applicationContext).also { INSTANCE = it }
            }
        }
    }


}