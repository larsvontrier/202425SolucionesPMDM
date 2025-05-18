package com.pepinho.pmdm.pokemonnav.repository

import com.pepinho.pmdm.pokemonnav.model.PokemonData


interface PokemonRepository {
    // Lista de pokemon con nombre y url
    suspend fun getAllPokemon(): List<PokemonData>
    // detalle de pokemon por id
    suspend fun getPokemonById(id: Int): PokemonData?
    suspend fun searchPokemon(query: String): List<PokemonData>
}

