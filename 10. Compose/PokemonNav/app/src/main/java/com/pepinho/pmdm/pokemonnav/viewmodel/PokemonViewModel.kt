package com.pepinho.pmdm.pokemonnav.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.pmdm.pokemonnav.model.PokemonData
import com.pepinho.pmdm.pokemonnav.repository.PokemonListRepository
import com.pepinho.pmdm.pokemonnav.repository.PokemonRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val repository: PokemonRepository
) : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<PokemonData>>(emptyList())
    val pokemonList: StateFlow<List<PokemonData>> = _pokemonList.asStateFlow()

    private val _searchResults = MutableStateFlow<List<PokemonData>>(emptyList())
    val searchResults: StateFlow<List<PokemonData>> = _searchResults.asStateFlow()

    private val _selectedPokemon = MutableStateFlow<PokemonData?>(null)
    val selectedPokemon: StateFlow<PokemonData?> = _selectedPokemon.asStateFlow()

    init {
        loadPokemon()
    }

    fun loadPokemon() {
        viewModelScope.launch {
            _pokemonList.value = repository.getAllPokemon()
        }
    }

    fun searchPokemon(query: String) {
        viewModelScope.launch {
            _searchResults.value = repository.searchPokemon(query)
        }
    }

    fun selectPokemon(id: Int) {
        viewModelScope.launch {
            _selectedPokemon.value = repository.getPokemonById(id)
        }
    }

}