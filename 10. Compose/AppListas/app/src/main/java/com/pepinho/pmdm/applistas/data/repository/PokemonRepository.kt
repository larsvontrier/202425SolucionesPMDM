package com.pepinho.pmdm.applistas.data.repository

import com.pepinho.pmdm.applistas.model.PokemonResponse

interface PokemonRepository {
    suspend fun getPokemons(limit: Int, offset: Int): PokemonResponse
}

