package com.pepinho.pmdm.applistas.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pepinho.pmdm.applistas.AppListas
import com.pepinho.pmdm.applistas.model.Pokemon
import com.pepinho.pmdm.applistas.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PokemonViewModel"

sealed class PokemonState {
    data object Loading : PokemonState()
    data class Success(
        val pokemons: List<Pokemon>,
        val isLoadingMore: Boolean = false
    ) : PokemonState()

    data class Error(val message: String) : PokemonState()
}

class PokemonViewModel(
    private val repository: PokemonRepository
) : ViewModel() {
    private val _state = MutableStateFlow<PokemonState>(PokemonState.Loading)
    val state: StateFlow<PokemonState> = _state.asStateFlow()

    private var currentOffset = 0
    private val limit = 100

    init {
        loadPokemons()
    }

    fun loadPokemons() {
        currentOffset = 0
        _state.value = PokemonState.Loading
        loadMorePokemons(reset = true)
    }

    fun loadMorePokemons(reset: Boolean = false) {
        if ((_state.value is PokemonState.Success) &&
            (state.value as PokemonState.Success).isLoadingMore
        ) return

        viewModelScope.launch {
            try {
                _state.value = when (val current = state.value) {
                    is PokemonState.Success -> current.copy(isLoadingMore = true)
                    else -> PokemonState.Loading
                }

                val response = repository.getPokemons(limit, currentOffset)
                val newPokemons = response.results

                _state.value = when (val current = state.value) {
                    is PokemonState.Success -> {
                        if (reset) {
                            PokemonState.Success(newPokemons)
                        } else {
                            current.copy(
                                pokemons = current.pokemons + newPokemons,
                                isLoadingMore = false
                            )
                        }
                    }

                    else -> PokemonState.Success(newPokemons)
                }

                currentOffset += limit
            } catch (e: Exception) {
                _state.value = PokemonState.Error(e.message ?: "Error al cargar Pokémon")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AppListas)
                val pokemonRepository = application.contenedor.pokemonRepository
                PokemonViewModel(repository = pokemonRepository)
            }
        }
    }
}

