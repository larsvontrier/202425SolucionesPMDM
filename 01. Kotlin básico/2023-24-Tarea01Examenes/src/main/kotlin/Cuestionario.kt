package com.pepinho.pmdm

class Cuestionario(val preguntas: MutableList<Pregunta>) {

    constructor(preguntas: Array<Pregunta>) : this(preguntas.toMutableList())

    // realizarExamen: recoge un array de objetos con las opciones marcadas y devuelve la puntuación obtenida.
    // El array pueden contener un array de enteros para las preguntas tipo test y o un Boolean para las preguntas de verdadero o falso.
    // Si el array de objetos recogido es nulo debe devolver 0. Para ello, recorre el array de preguntas y
    // devuelve los puntos de cada pregunta obtenidos a través de los puntos de cada pregunta. Ten en cuenta que los puntos
    // Son diferentes en función del tipo de pregunta.

    fun realizarExamen(marcadas: Array<Any>): Double {
        var puntuacion = 0.0


        preguntas.forEachIndexed { index, pregunta ->
            val marcada = marcadas[index]
            puntuacion += when (pregunta) {
                is PreguntaTest -> pregunta.getPuntos(marcada as IntArray)
                is PreguntaVerdaderoFalso -> if (pregunta.test(marcada as Boolean)) pregunta.puntos else 0.0
                else -> 0.0
            }
        }
        return puntuacion
//        if (marcadas == null) return 0.0
//        var puntuacion = 0.0
//
//        for (i in preguntas.indices) {
//            val pregunta = preguntas[i]
//            val marcada = marcadas[i]
//            puntuacion += when(pregunta) {
//                 is PreguntaTest -> pregunta.getPuntos(marcada as IntArray)
//                 is PreguntaVerdaderoFalso -> {
//                     if(pregunta.test(marcada as Boolean)) pregunta.puntos else 0.0
//                 }
//                else -> 0.0
//            }
//        }
//        return puntuacion
    }

    fun addPregunta(pregunta: Pregunta): Boolean = preguntas.add(pregunta)
    fun removePregunta(pregunta: Pregunta): Boolean = preguntas.remove(pregunta)
    fun removePregunta(numero: Int): Boolean = preguntas.removeIf { it.numero == numero }
    fun getPregunta(numero: Int): Pregunta? = preguntas.find { it.numero == numero }
    fun getPregunta(enunciado: String): Pregunta? = preguntas.find { it.enunciado == enunciado }
    fun getPreguntas(): List<Pregunta> = preguntas.sorted()

    fun setPreguntas(preguntas: List<Pregunta>) {
        this.preguntas.clear()
        this.preguntas.addAll(preguntas)
    }

    fun clearPreguntas() {
        preguntas.clear()
    }

    fun getNumPreguntas(): Int = preguntas.size

    override fun toString(): String {
        return "Examen:\n" + preguntas.joinToString("\n")
    }

}