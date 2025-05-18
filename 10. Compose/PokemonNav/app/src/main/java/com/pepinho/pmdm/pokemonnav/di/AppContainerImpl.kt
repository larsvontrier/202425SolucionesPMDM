package com.pepinho.pmdm.pokemonnav.di

import com.pepinho.pmdm.pokemonnav.repository.PokemonListRepository
import com.pepinho.pmdm.pokemonnav.repository.PokemonRepository

class AppContainerImpl: AppContainer {

    override val pokemonRepository: PokemonRepository by lazy {
        PokemonListRepository()
    }

}