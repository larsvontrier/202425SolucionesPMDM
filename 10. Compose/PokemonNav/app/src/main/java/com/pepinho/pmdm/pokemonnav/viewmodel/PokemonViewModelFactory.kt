package com.pepinho.pmdm.pokemonnav.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pepinho.pmdm.pokemonnav.repository.PokemonRepository

class PokemonViewModelFactory(
    private val pokemonRepository: PokemonRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            return PokemonViewModel(pokemonRepository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}