package com.pepinho.nba

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pepinho.nba.model.Equipo

class EquipoViewModelFactory(private val idEquipo: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EquipoViewModel::class.java)){
            return EquipoViewModel(idEquipo) as T
        }
        return super.create(modelClass)
    }
}