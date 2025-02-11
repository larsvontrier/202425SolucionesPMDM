package com.pepinho.nba.model

// Extensión para convertir un enum en una lista de strings
inline fun <reified T : Enum<T>> enumToList(): List<String> {
    return enumValues<T>().map { it.name }
}


inline fun <reified T : Enum<T>> enumByNameIgnoreCase(input: String, default: T? = null): T? {
    return enumValues<T>().firstOrNull { it.name.equals(input, true) } ?: default
}