package com.pepinho.nba

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pepinho.nba.model.repositorios.EquipoRepository

class EquipoViewModelFactory(private val repository: EquipoRepository, private val idEquipo: Int = 0) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EquipoViewModel::class.java)) {
            return EquipoViewModel(repository, idEquipo) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }

}
