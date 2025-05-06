package com.pepinho.pmdm.applistas.model

import kotlinx.serialization.Serializable

/**
 * {
 *   "count": 1302,
 *   "next": "https://pokeapi.co/api/v2/pokemon?offset=200&limit=200",
 *   "previous": null,
 *   "results": [
 *     {
 *       "name": "bulbasaur",
 *       "url": "https://pokeapi.co/api/v2/pokemon/1/"
 *     },
 *     {
 *       "name": "ivysaur",
 *       "url": "https://pokeapi.co/api/v2/pokemon/2/"
 *     },
 *     {
 *       "name": "venusaur",
 *       "url": "https://pokeapi.co/api/v2/pokemon/3/"
 *     },..
 *     ]
 *     }
 *
 */
@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)