package com.pepinho.pmdm.model

enum class Division {
    ATLANTIC, SOUTHEAST, CENTRAL, PACIFIC, NORTHWEST, SOUTHWEST;

    companion object {
        fun of(nome: String?): Division {
            return values().firstOrNull { it.name.equals(nome, true) } ?: Division.PACIFIC
        }
    }

}