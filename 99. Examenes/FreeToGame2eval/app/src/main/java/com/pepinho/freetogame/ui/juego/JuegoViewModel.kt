package com.pepinho.freetogame.ui.juego

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.freetogame.model.JuegoConDetalles
import com.pepinho.freetogame.repositories.JuegoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class JuegoViewModel (repository: JuegoRepository, idJuego: Int) : ViewModel()  {
    // Convierte el Flow del repositorio en un StateFlow
    val juego: StateFlow<JuegoConDetalles?> = repository.getJuegoConDetallesByIdJuego(idJuego)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily, // Inicia solo cuando hay observadores
            initialValue = null
        )

    // Estado de carga basado en si lel juego es nulo
    val loadingState: StateFlow<Boolean> = juego
        .map { it == null }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = true
        )
}