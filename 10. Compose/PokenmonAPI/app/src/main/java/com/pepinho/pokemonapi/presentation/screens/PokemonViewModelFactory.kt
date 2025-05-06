package com.pepinho.pokemonapi.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pepinho.pokemonapi.data.repository.PokemonRepository

class PokemonViewModelFactory(
    private val repository: PokemonRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when(modelClass) {
            PokemonViewModel::class.java -> {
                return PokemonViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}