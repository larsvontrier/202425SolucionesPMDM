package com.pepinho.nba

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.nba.model.Equipo
import com.pepinho.nba.model.repositorios.EquipoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EquiposViewModel(private val repositorio: EquipoRepository) : ViewModel() {
    private val _equipos = MutableStateFlow<List<Equipo>>(emptyList())

    //    private val _equipos = repositorio.equipos.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val equipos: StateFlow<List<Equipo>> get() = _equipos

    private val _loadingState = MutableStateFlow<Boolean>(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    init {
        getEquipos()
    }

    fun getEquipos() {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                // Los cambios en el repositorio de equipos se reflejarán en _equipos
                repositorio.equipos.collect { lista ->
                    _equipos.value = lista
                }
            } catch (e: Exception) {
                Log.e("EquiposViewModel", "${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

}