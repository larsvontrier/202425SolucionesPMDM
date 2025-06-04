package com.pepinho.pokemonapi.data.repository

import com.pepinho.pokemonapi.data.api.PokemonApi
import com.pepinho.pokemonapi.model.PokemonDataResponse
import com.pepinho.pokemonapi.model.PokemonResponse

class PokemonRepositoryImpl (
    private val api: PokemonApi
) : PokemonRepository {
    override suspend fun getPokemons(limit: Int, offset: Int): PokemonResponse =
        api.fetchPokemons(limit = limit, offset = offset)

    override suspend fun getPokemonById(id: Int): PokemonDataResponse  = api.getPokemonById(id)

}