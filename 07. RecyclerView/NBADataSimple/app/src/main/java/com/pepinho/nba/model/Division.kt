package com.pepinho.nba.model

enum class Division {
    ATLANTIC, SOUTHEAST, CENTRAL, PACIFIC, NORTHWEST, SOUTHWEST;

    companion object {
        fun of(nome: String?): Division {
            return entries.firstOrNull { it.name.equals(nome, true) } ?: PACIFIC
        }
    }

}
