package com.pepinho.freetogame.ui.juegos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pepinho.freetogame.model.Juego
import com.pepinho.freetogame.model.Plataforma
import com.pepinho.freetogame.repositories.JuegoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class JuegosViewModel(repository: JuegoRepository, idPlataforma: Int) : ViewModel() {
    // Convierte el Flow del repositorio en un StateFlow
    val juegos: StateFlow<List<Juego>> = repository.getAllByIdPlataforma(idPlataforma)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily, // Inicia solo cuando hay observadores
            initialValue = emptyList()
        )

    // Estado de carga basado en si la lista está vacía
    val loadingState: StateFlow<Boolean> = juegos
        .map { it.isEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = true
        )
}