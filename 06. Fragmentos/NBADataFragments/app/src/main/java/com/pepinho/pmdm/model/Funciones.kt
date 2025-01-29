package com.pepinho.pmdm.model

// Extensión para convertir un enum en una lista de strings
inline fun <reified T : Enum<T>> enumToList(): List<String> {
    return enumValues<T>().map { it.name }
}