package com.pepinho.nba

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.nba.model.Equipo
import com.pepinho.nba.model.repositorios.EquipoJsonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EquipoViewModel(idEquipo: Int): ViewModel() {
    private val repository: EquipoJsonRepository = EquipoJsonRepository.getInstance()

    private val _equipo = MutableStateFlow<Equipo?>(null)
    val equipo: StateFlow<Equipo?> = _equipo.asStateFlow()

    init {
        getEquipoById(idEquipo)
    }

    private fun getEquipoById(idEquipo: Int){
        viewModelScope.launch {
            _equipo.value = repository.getEquipoById(idEquipo)
        }

    }
}