package com.pepinho.pmdm.applistas.data

import com.pepinho.pmdm.applistas.data.repository.PokemonRepository

/**
 * Usado para inyectar dependencias en la aplicación.
 * En este caso sólo añade el repositorio de Pokémon.
 */
interface AppContainer {
    val pokemonRepository: PokemonRepository
}