package com.pepinho.pmdm.applistas.data

import com.pepinho.pmdm.applistas.model.PokemonResponse

class PokemonRepositoryImpl (
    private val api: PokemonApi
) : PokemonRepository {
    override suspend fun getPokemons(limit: Int, offset: Int): PokemonResponse =
        api.fetchPokemons(limit = limit, offset = offset)
}