package com.pepinho.nba.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pepinho.nba.ui.equipos.EquiposViewModelStateIn
import com.pepinho.nba.model.repositorios.EquipoRepository
import com.pepinho.nba.ui.equipo.EquipoViewModel
import com.pepinho.nba.ui.equipos.EquiposViewModel

class EquipoViewModelFactory(private val repository: EquipoRepository, private val idEquipo: Int = 0) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(EquipoViewModel::class.java) -> {
                EquipoViewModel(repository, idEquipo) as T
            }
            modelClass.isAssignableFrom(EquiposViewModel::class.java) -> {
                Log.i("EquiposViewModel", "OK");
                EquiposViewModel(repository) as T
            }
            modelClass.isAssignableFrom(EquiposViewModelStateIn::class.java) -> {
                Log.i("EquiposViewModelStateIn", "OK");
                EquiposViewModelStateIn(repository) as T
            }
            else -> throw IllegalArgumentException("Clase ViewModel desconocida")
        }
    }

}
