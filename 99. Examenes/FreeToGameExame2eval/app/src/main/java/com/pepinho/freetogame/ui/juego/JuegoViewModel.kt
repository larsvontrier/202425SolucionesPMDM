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

}