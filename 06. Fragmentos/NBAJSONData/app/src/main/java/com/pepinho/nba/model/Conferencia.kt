package com.pepinho.nba.model

enum class Conferencia {
    EAST, WEST;

    companion object {
        fun of(nome: String?): Conferencia {
            return values().firstOrNull { it.name.equals(nome, true) } ?: WEST
        }
    }
}
