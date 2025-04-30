package com.pepinho.nba.ui.equipos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.nba.model.Equipo
import com.pepinho.nba.model.repositorios.EquipoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class EquiposViewModelStateIn(private val repositorio: EquipoRepository) : ViewModel() {

    // Convierte el Flow del repositorio en un StateFlow
    val equipos: StateFlow<List<Equipo>> = repositorio.equipos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily, // Inicia solo cuando hay observadores
            initialValue = emptyList()
        )

    // Estado de carga basado en si la lista está vacía
    val loadingState: StateFlow<Boolean> = equipos
        .map { it.isEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = true
        )
}