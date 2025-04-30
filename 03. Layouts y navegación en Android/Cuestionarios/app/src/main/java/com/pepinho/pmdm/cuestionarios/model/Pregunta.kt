package com.pepinho.pmdm.cuestionarios.model

import java.util.*

/**
 * Clase abstracta que representa una pregunta.
 * Como podéis comprobar:
 *  - La clase es abstracta, por lo que no se puede instanciar.
 *  - El parámetro número se asigna en un bloque init, que se ejecuta al final de la inicialización de la clase.
 *  - El parámetro enunciado se asigna en el constructor y no cambia, por lo que es una propiedad de solo lectura.
 * @param enunciado El enunciado de la pregunta.
 * @param numero El número de la pregunta.
 * @constructor Crea una pregunta con el enunciado y el número.
 */
abstract class Pregunta(val enunciado: String, numero: Int) : Comparable<Pregunta> {
    /*
     Los companion object permiten definir funciones y propiedades estáticas en una clase.
     Facilita la creación de métodos factory, es decir, métodos que devuelven una instancia de la clase,
     así como constantes y métodos de utilidad compartidos.
     Ejemplo de Factoy Method: fun create() = Pregunta("Enunciado", 1)
     class MiClase(val nombre: String) {
        companion object Factory { // Se puede omitir el nombre Factory
            fun create(nombre: String): MiClase = MiClase(nombre)
        }
     }
     Se invocaría de la siguiente forma:
     fun main() {
        val miClase = MiClase.create("Nombre") // o MiClase.Factory.create("Nombre")
     }
     */
    companion object {
        const val DEFAULT_VALUE = 1
    }
    var idPregunta: Long = 0 // No se inicializa
    var descripcion: String? = null
    var numero: Int = DEFAULT_VALUE
        set(value) {
            field = if (value < 1) DEFAULT_VALUE else value
        }
    var puntos: Double = 1.0
        set(value) {
            field = if (value <= 0) DEFAULT_VALUE.toDouble() else value
        }

    /**
     * El número que recoge el constructor no tiene val ni var, porque queremos realizar comprobaciones en el setter.
     * En realidad esta comprobación no es necesaria, pero la he añadido para que veáis cómo se puede hacer.
     */
    init {
        // Hace una invocación al método set, por lo que no se precisa realizar la comprobación de que el
        // número sea mayor que 0.
        this.numero = numero
    }

    /* No serían necesarios si no quisieramos un patrón builder que devuelva al objeto receptor.
     * apply es una función de extensión que permite inicializar propiedades de un objeto o configurar su estado como
     * un patrón builder: inline fun <T> T.apply(block: T.() -> Unit): T
     * Se usa normalmente para la configuración de objetos durante su inicialización:
     * val persona = Persona().apply { nombre = "Pepe"; edad = 30 }
     * En este caso, se usa para devolver el objeto receptor, es decir, la propia pregunta (this).
     *
     * Por ello, se puede acceder a las propiedades y métodos del objeto en la expresión lambda sin el uso de this.
     */

    abstract fun getCorrectIndex() : Int

    fun setDescripcion(descripcion: String) = apply { this.descripcion = descripcion }
    fun setNumero(numero: Int) = apply { this.numero = numero }
    fun setPuntos(puntos: Double) = apply { this.puntos = puntos }

    // También se puede hacer de esta forma, pero es más "verbosa" ;-)
    // apply es una función de extensión que devuelve el objeto receptor: inline fun <T> T.apply(block: T.() -> Unit): T


//    fun setDescripcion(descripcion: String): Pregunta {
//        this.descripcion = descripcion
//        return this
//    }
//
//    fun setNumero(numero: Int): Pregunta {
//        this.numero = numero
//        return this
//    }
//
//    fun setPuntos(puntos: Double): Pregunta {
//        this.puntos = puntos
//        return this
//    }

    override fun toString() = if (enunciado.isEmpty()) "Pregunta sin enunciado"
    else "$numero. ${enunciado.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}"

    override fun compareTo(other: Pregunta) = this.numero - other.numero
}



