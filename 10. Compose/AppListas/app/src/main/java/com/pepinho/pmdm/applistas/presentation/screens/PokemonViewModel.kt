package com.pepinho.pmdm.applistas.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.pmdm.applistas.api.Pokemon
import com.pepinho.pmdm.applistas.api.PokemonApi
import com.pepinho.pmdm.applistas.api.PokemonRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "GaleriaFotosViewModel"

class PokemonViewModel : ViewModel() {
    private val pokemonRepository = PokemonRepositoryImpl(PokemonApi.getInstance())

    private val _galleryItems: MutableStateFlow<List<Pokemon>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<Pokemon>>
        get() = _galleryItems.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val items = pokemonRepository.getPokemons().results
                Log.d(TAG, "Pokemons recibidos: $items")
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Error al obtener los Pokemons", ex)
            }
        }
    }
}