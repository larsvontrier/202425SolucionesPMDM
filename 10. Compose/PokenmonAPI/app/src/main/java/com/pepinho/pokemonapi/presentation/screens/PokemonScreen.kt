package com.pepinho.pokemonapi.presentation.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.pepinho.pokemonapi.R
import com.pepinho.pokemonapi.model.Pokemon


@Composable
fun PokemonScreen(viewModel: PokemonViewModel = viewModel(), modifier: Modifier = Modifier) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is PokemonState.Loading -> FullScreenLoading()
        is PokemonState.Error -> ErrorScreen(message = (state as PokemonState.Error).message) { viewModel.loadPokemons() }
        is PokemonState.Success -> {
            val successState = state as PokemonState.Success
            PokemonGrid(
                pokemons = successState.pokemons,
                isLoadingMore = successState.isLoadingMore,
                onLoadMore = { viewModel.loadMorePokemons() }
            )
        }
    }
}

@Composable
fun PokemonGrid(
    pokemons: List<Pokemon>,
    isLoadingMore: Boolean,
    onLoadMore: () -> Unit
) {
    // 1. Estado para el grid regular (no staggered)
    val gridState = rememberLazyGridState()

    // 2. Efecto para detectar el final de la lista
    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (visibleItems.isNotEmpty() &&
                    visibleItems.last().index >= gridState.layoutInfo.totalItemsCount - 3) {
                    onLoadMore()
                }
            }
    }

    // 3. Grid con el estado
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        state = gridState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(pokemons.size) { index ->
            PokemonItem(pokemons[index])
        }

        item {
            if (isLoadingMore) {
                LoadingMoreIndicator()
            }
        }
    }
}

@Composable
fun LoadingMoreIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick : () -> Unit = {}) {
    val pokemonId = remember(pokemon.url) {
        pokemon.url.split("/").dropLast(1).last().toInt()
    }
    val imageUrl = remember(pokemonId) {
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokemonId.png"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f).padding(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .size(96.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.error_image), // Icono de fallback
                placeholder = painterResource(R.drawable.placeholder) // Placeholder
            )

            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Cargando Pokémon...")
        }
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error: $message",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Reintentar")
        }
    }
}