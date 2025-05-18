package com.pepinho.pmdm.pokemonnav.di

import com.pepinho.pmdm.pokemonnav.repository.PokemonRepository


interface AppContainer {
    val pokemonRepository: PokemonRepository
}