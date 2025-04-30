package com.pepinho.ciclodevida

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pepinho.ciclodevida.repositorio.PreguntaRepository
import com.pepinho.pmdm.cuestionarios.model.PreguntaTest
import com.pepinho.pmdm.cuestionarios.model.PreguntaVerdaderoFalso

private const val TAG = "CuestionarioSavedStateViewModel"
private const val INDICE_ACTUAL_KEY = "INDICE_ACTUAL_KEY"

class CuestionarioSavedStateViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val preguntasRepository = PreguntaRepository
    private var iActual: Int
        get() = savedStateHandle.get(INDICE_ACTUAL_KEY) ?: 0
        set(value) = savedStateHandle.set(INDICE_ACTUAL_KEY, value)

    init {
        Log.d(TAG, "Instancia de SavedStateViewModel creada")
    }

    val numeroPregunta: Int
        get() = iActual + 1

    val respuestaCorrecta: Int
        get() = PreguntaRepository.getPreguntaByIndex(iActual)?.getCorrectIndex() ?: -1

    val enunciado: String
        get() = PreguntaRepository.getPreguntaByIndex(iActual)?.enunciado ?: "-"

    val getOpciones: Array<String>
        get() = when (val pregunta = PreguntaRepository.getPreguntaByIndex(iActual)) {
            is PreguntaTest -> {
                pregunta.opciones.map { it?.enunciado ?: "-" }.toTypedArray()
            }

            is PreguntaVerdaderoFalso -> {
                arrayOf("Verdadero", "Falso")
            }

            else -> emptyArray()

        }

    val last: Boolean
        get() = PreguntaRepository.size - 1 == iActual

    fun moveNext() {
        if (iActual < PreguntaRepository.size - 1) iActual++
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "Instancia de ViewModel destruida")
    }

}