package com.pepinho.pokemonapi.model

data class PokemonData(
    val id: Int,
    val name: String,
    val type: String,
    val imageUrl: String,
    val description: String,
    val height: Double,
    val weight: Double,
    val stats: Map<String, Int>
)
