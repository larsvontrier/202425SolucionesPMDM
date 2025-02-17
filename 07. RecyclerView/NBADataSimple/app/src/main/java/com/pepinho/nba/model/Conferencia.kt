package com.pepinho.nba.model

enum class Conferencia {
    EAST, WEST;

    companion object {
        fun of(nome: String?): Conferencia {
            return entries.firstOrNull { it.name.equals(nome, true) } ?: WEST
        }
    }
}
