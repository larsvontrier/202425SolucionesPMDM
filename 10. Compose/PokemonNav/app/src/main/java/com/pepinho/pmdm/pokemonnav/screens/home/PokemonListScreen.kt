package com.pepinho.pmdm.pokemonnav.screens.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pepinho.pmdm.pokemonnav.ui.components.PokemonCard
import com.pepinho.pmdm.pokemonnav.viewmodel.PokemonViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel,
    onPokemonClick: (Int) -> Unit
) {
    val pokemonList by viewModel.pokemonList.collectAsState()

    Scaffold { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            items(pokemonList) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onClick = { onPokemonClick(pokemon.id) }
                )
            }
        }
    }
}