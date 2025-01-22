package com.pepinho.ciclodevida

import android.util.Log
import androidx.lifecycle.ViewModel
import com.pepinho.ciclodevida.repositorio.PreguntaRepository
import com.pepinho.pmdm.cuestionarios.model.PreguntaTest
import com.pepinho.pmdm.cuestionarios.model.PreguntaVerdaderoFalso

private const val TAG = "CuestionarioViewModel"

class CuestionarioViewModel : ViewModel() {

    private val preguntasRepository = PreguntaRepository
    private var iActual: Int = 0

    init {
        Log.d(TAG, "Instancia de ViewModel creada")
    }

    val numeroPregunta: Int
        get() = iActual + 1

    val respuestaCorrecta: Int
        get() = preguntasRepository.getPreguntaByIndex(iActual)?.getCorrectIndex() ?: -1

    val enunciado: String
        get() = preguntasRepository.getPreguntaByIndex(iActual)?.enunciado ?: "-"

    val getOpciones: Array<String>
        get() = when (val pregunta = preguntasRepository.getPreguntaByIndex(iActual)) {
            is PreguntaTest -> {
                pregunta.opciones.map { it?.enunciado ?: "-" }.toTypedArray()
            }

            is PreguntaVerdaderoFalso -> {
                arrayOf("Verdadero", "Falso")
            }

            else -> emptyArray()

        }

    val last: Boolean
        get() = preguntasRepository.size - 1 == iActual

    fun moveNext() {
        if (iActual < preguntasRepository.size - 1) iActual++
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "Instancia de ViewModel destruida")
    }

}