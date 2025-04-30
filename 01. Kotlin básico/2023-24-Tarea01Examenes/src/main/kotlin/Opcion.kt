package com.pepinho.pmdm

/**
 * Clase que representa una opción de una pregunta de test.
 * Una data class es una clase que solo tiene propiedades y métodos de acceso a las mismas.
 * Entre otras cosas, se generan automáticamente los métodos equals, hashCode y toString. La principal ventaja de las "data class" es que permiten
 * crear clases que solo contienen datos, sin funcionalidad, de forma concisa. Es lo que en Java se conoce como POJO (Plain Old Java Object).
 * Una de las diferencias con las clases normales es que no se pueden declarar como abstractas, ni selladas, ni internas.
 * Además, por ejemplo, la implementación de equals() y hashCode() se basa en los valores de las propiedades, no en la identidad del objeto.
 * Por lo que una sentencia del tipo "Opcion("texto", true) == Opcion("texto", true)" devolvería true.
 * @param enunciado Texto de la opción.
 * @param correcta Indica si la opción es correcta o no.
 * @constructor Crea una opción con el enunciado y si es correcta o no.
 * @see PreguntaTest
 */
data class Opcion(val enunciado: String, val correcta: Boolean) {
    /**
     * Devuelve una cadena con el enunciado de la opción.
     * Como podemos ver, existe inferencia de tipos en Kotlin, por lo que no es necesario declarar el tipo de
     * retorno de la función.
     * Si la opción es correcta, se añade un asterisco al final.
     * @return Cadena con el enunciado de la opción.
     */
    override fun toString() = if (correcta) "$enunciado [*]" else enunciado
}
