package com.pepinho.pokemonapi.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pepinho.pokemonapi.AppPokemon
import com.pepinho.pokemonapi.model.Pokemon
import com.pepinho.pokemonapi.data.repository.PokemonRepository
import com.pepinho.pokemonapi.model.PokemonData
import com.pepinho.pokemonapi.model.toPokemonData
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

sealed class PokemonDataState {
    data object Loading : PokemonDataState()
    data class Success(val pokemon: PokemonData) : PokemonDataState()
    data class Error(val message: String) : PokemonDataState()
}

class PokemonViewModel(
    private val repository: PokemonRepository
) : ViewModel() {
    private val _state = MutableStateFlow<PokemonState>(PokemonState.Loading)
    val state: StateFlow<PokemonState> = _state.asStateFlow()

    private val _statePokemon = MutableStateFlow<PokemonDataState>(PokemonDataState.Loading)
    val statePokemon: StateFlow<PokemonDataState> = _statePokemon.asStateFlow()

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
                _state.value = PokemonState.Error(e.message ?: "Error al cargar la lista de Pokémon")
            }
        }
    }

    fun getPokemonById(id: Int) {
        viewModelScope.launch {
            try {
                _statePokemon.value = PokemonDataState.Loading
                val response = repository.getPokemonById(id)
                _statePokemon.value = PokemonDataState.Success(response.toPokemonData())
            } catch (e: Exception) {
                _statePokemon.value = PokemonDataState.Error(e.message ?: "Error al cargar Pel okémon")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AppPokemon)
                val pokemonRepository = application.contenedor.pokemonRepository
                PokemonViewModel(repository = pokemonRepository)
            }
        }
    }
}

