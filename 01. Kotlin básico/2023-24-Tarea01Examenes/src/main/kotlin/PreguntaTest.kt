package com.pepinho.pmdm

import java.util.function.Predicate

/**
 * Clase que representa una pregunta de test.
 * El número de opciones por defecto es 4, declarado como un atributo de la clase.
 * @param enunciado Texto de la pregunta.
 * @param numero Número de la pregunta.
 * @constructor Crea una pregunta con el enunciado y el número.
 */
class PreguntaTest(enunciado: String, numero: Int, numOpciones: Int = NUMERO_OPCIONES) : Pregunta(enunciado, numero),
    Predicate<Int> {
    companion object {
        const val NUMERO_OPCIONES = 4
    }

    // Ojo, no es lo mismo un array de nulables que un array nulable: Array<Opcion?> vs Array<Opcion>?
    val opciones: Array<Opcion?> = Array(numOpciones) { null } // inicializa el array con nulos

    // Con MutableList sería más sencillo, ya que no hay que preocuparse de los nulos:
    // val opciones: MutableList<Opcion?> = MutableList(numOpciones) { null }

    init {
        if (numOpciones < 1) throw IllegalArgumentException("El número de opciones debe ser mayor que 0")
        // Otra opción más idiomática en Kotlin:
        //require(numOpciones > 0) { "El número de opciones debe ser mayor que 0" } // Kotlin 1.5, lanza una excepción de tipo IllegalArgumentException
    }

    // Con MutableList:
    // fun addOpcion(opcion: Opcion): Boolean = if (opciones.size < numOpciones) opciones.add(opcion) else false
    // fun addOpcion(opcion: Opcion) = opciones.contains(null).also { if (it) opciones[opciones.indexOf(null)] = opcion }
//    fun addOpcion(opcion: Opcion): Boolean {
//        for (i in opciones.indices) {
//            if (opciones[i] == null) {
//                opciones[i] = opcion
//                return true
//            }
//        }
//        return false
//    }
    fun addOpcion(opcion: Opcion): Boolean {
        val index = opciones.indexOfFirst { it == null }
        return if (index != -1) {
            opciones[index] = opcion
            true
        } else {
            false
        }
    }

    // Con MutableList:
    // fun getNumCorrectas() = opciones.count { it?.correcta == true }
    fun getNumCorrectas(): Int = opciones.count { it?.correcta == true }

    fun getPuntos(marcadas: IntArray): Double {
        val numCorrectas = getNumCorrectas()
        if (numCorrectas == 0) return 0.0
        val marcadasBien = marcadas.count { opciones[it]?.correcta == true }
        val marcadasMal = marcadas.count { opciones[it]?.correcta == false }
        return (puntos * (marcadasBien - marcadasMal) / numCorrectas)
    }
    // Con MutableList:
//    fun getPuntos(marcadas: Array<Int>) = marcadas?.let { // let es una función de extensión que permite ejecutar un bloque de código si el objeto no es nulo
//        val marcadasBien = it.count { opciones[it]?.correcta == true }
//        val marcadasMal = it.count { opciones[it]?.correcta == false }
//        puntos * (marcadasBien - marcadasMal) / getNumCorrectas()
//    } ?: 0.0

//    override fun toString() = buildString {
//        append(super.toString()).append("\n")
//        opciones.forEachIndexed { index, opcion -> append("    ${index + 1}. ${opcion}\n") }
//    }
//
//    fun test(value: Int) = value in 0 until opciones.size && opciones[value]?.correcta == true

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(super.toString()).append(System.lineSeparator())
        opciones.forEachIndexed { index, opc ->
            sb.append("    ${index + 1}. ${opc}\n")
        }
        return sb.toString()
    }

    /*
    Con apply:
    override fun toString() = super.toString().apply {
        opciones.forEachIndexed { index, opcion -> append("    ${index + 1}. $opcion\n") }
    }
     */

    override fun test(value: Int): Boolean {
        return value in opciones.indices && opciones[value]?.correcta == true
    }
}
