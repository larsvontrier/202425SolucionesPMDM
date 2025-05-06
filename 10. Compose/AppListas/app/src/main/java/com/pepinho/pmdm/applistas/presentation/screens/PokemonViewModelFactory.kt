package com.pepinho.pmdm.applistas.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pepinho.pmdm.applistas.data.repository.PokemonRepository

class PokemonViewModelFactory(
    private val repository: PokemonRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonViewModel(repository) as T
    }
}