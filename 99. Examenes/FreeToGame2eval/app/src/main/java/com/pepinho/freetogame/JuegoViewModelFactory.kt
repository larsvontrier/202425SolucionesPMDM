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

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(JuegoViewModel::class.java) -> {
                Log.i("JuegoViewModel", "OK: $idJuego");
                JuegoViewModel(repository = repository, idJuego = idJuego) as T
            }
            modelClass.isAssignableFrom(JuegosViewModel::class.java) -> {
                Log.i("JuegosViewModel", "OK: $idPlataforma");
                JuegosViewModel(repository = repository, idPlataforma = idPlataforma) as T
            }
            else -> throw IllegalArgumentException("Clase ViewModel desconocida")
        }
    }

}
