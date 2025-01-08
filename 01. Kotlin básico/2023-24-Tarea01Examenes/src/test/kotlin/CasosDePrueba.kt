import com.pepinho.pmdm.Cuestionario
import com.pepinho.pmdm.Opcion
import com.pepinho.pmdm.PreguntaTest
import com.pepinho.pmdm.PreguntaVerdaderoFalso

fun main() {
    val pregunta1: PreguntaTest = PreguntaTest("Marca las respuestas correctas sobre la sintaxis de Kotlin:", 1)
    pregunta1.addOpcion(Opcion("Kotlin es un lenguaje de programación desarrollado por JetBrains", true))
    pregunta1.addOpcion(Opcion("Las colecciones mutables en Kotlin son List, Set y Map", false))
    pregunta1.addOpcion(Opcion("La palabra reservada apply se utiliza para aplicar una función a un objeto", true))
    pregunta1.addOpcion(Opcion("El operador Elvis ?: se utiliza para asignar un valor por defecto a una variable", true))
    pregunta1.puntos = 3.0
    println(pregunta1)

    val pregunta2 = PreguntaVerdaderoFalso("Los astronautas del Apolo 11 llegaron a la Luna en 1967", 2)
    pregunta2.correcta = false
    println(pregunta2)

    val pregunta3 = PreguntaTest("¿Cuál es el libro más vendido de la historia?", 3)
    pregunta3.addOpcion(Opcion("Don Quijote de la Mancha", true))
    pregunta3.addOpcion(Opcion("Historia de dos ciudades", false))
    pregunta3.addOpcion(Opcion("El Señor de los Anillos", false))
    pregunta3.addOpcion(Opcion("El Principito", false))
    println(pregunta3)

    val pregunta4 = PreguntaVerdaderoFalso("¿Es Kotlin el lenguaje de programación más usado en 2023?", 4)
    pregunta4.correcta = false
    println(pregunta4)


    val pregunta5 = PreguntaTest("¿Cuál es el río más largo del mundo?", 5)
    pregunta5.addOpcion(Opcion("Nilo", true))
    pregunta5.addOpcion(Opcion("Amazonas", false))
    pregunta5.addOpcion(Opcion("Yangtsé", false))
    pregunta5.addOpcion(Opcion("Misisipi", false))
    pregunta5.setPuntos(1.5).numero = 5 //
    println(pregunta5)

    val examen = Cuestionario(mutableListOf(pregunta1, pregunta2, pregunta3, pregunta4, pregunta5))

    val marcadas: Array<Any> = arrayOf(intArrayOf(0, 2, 3), false, intArrayOf(0), false, intArrayOf(0))

    println("Puntuación uno: ${examen.realizarExamen(marcadas)}") // 7.5

    val marcadas2: Array<Any> = arrayOf(intArrayOf(1, 2, 3), false, intArrayOf(0), false, intArrayOf(0))

    println("Puntuación dos: ${examen.realizarExamen(marcadas2)}") // 5.5
    // (puntos * (marcadasBien - marcadasMal) / numCorrectas) = 3.0 * (2 - 1) / 3 = 1.0
    // 1 + 1 + 1 + 1 + 1.5 = 5.5

}