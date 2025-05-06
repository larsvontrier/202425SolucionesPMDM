package com.pepinho.pokemonapi.data.repository

import com.pepinho.pokemonapi.model.PokemonResponse

interface PokemonRepository {
    suspend fun getPokemons(limit: Int, offset: Int): PokemonResponse
}

