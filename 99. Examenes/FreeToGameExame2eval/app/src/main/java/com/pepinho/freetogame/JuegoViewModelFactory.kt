package com.pepinho.freetogame

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pepinho.freetogame.model.Juego
import com.pepinho.freetogame.model.Plataforma
import com.pepinho.freetogame.repositories.JuegoRepository
import com.pepinho.freetogame.ui.juego.JuegoViewModel
import com.pepinho.freetogame.ui.juegos.JuegosViewModel

class JuegoViewModelFactory(private val repository: JuegoRepository, private val idPlataforma: Int = 0,
                            private val idJuego: Int = 0) : ViewModelProvider.Factory {

// ..

}
