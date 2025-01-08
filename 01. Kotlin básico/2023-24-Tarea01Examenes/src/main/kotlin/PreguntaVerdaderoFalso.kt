package com.pepinho.pmdm

import java.util.function.Predicate

class PreguntaVerdaderoFalso(enunciado: String, numero: Int, var correcta: Boolean = true) : Pregunta(enunciado, numero), Predicate<Boolean> {
//    val correcta: Boolean = false

    // fun setCorrecta(correcta: Boolean) = apply { this.correcta = correcta }
//    fun setCorrecta(correcta: Boolean): PreguntaVerdaderoFalso {
//        this.correcta = correcta
//        return this
//    }

    override fun toString() = "${super.toString()} [${if (correcta) "V" else "F"}]"

    override fun test(t: Boolean) = t == correcta

//    override fun compareTo(other: Pregunta): Int {
//        return super.compareTo(other)
//    }
}