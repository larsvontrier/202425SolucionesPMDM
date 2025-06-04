package com.pepinho.pokemonapi.presentation.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pepinho.pokemonapi.R
import com.pepinho.pokemonapi.model.PokemonData
import com.pepinho.pokemonapi.presentation.screens.PokemonDataState
import com.pepinho.pokemonapi.presentation.screens.PokemonState
import com.pepinho.pokemonapi.presentation.screens.PokemonViewModel
import com.pepinho.pokemonapi.presentation.screens.pokemon.ErrorScreen
import com.pepinho.pokemonapi.presentation.screens.pokemon.FullScreenLoading
import com.pepinho.pokemonapi.presentation.screens.pokemon.PokemonGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
//    pokemon: PokemonDataState?,
    viewModel: PokemonViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.statePokemon.collectAsState()

    when (state) {
        is PokemonDataState.Loading -> FullScreenLoading()
        is PokemonDataState.Error -> ErrorScreen(message = (state as PokemonDataState.Error).message) { viewModel.loadPokemons() }
        is PokemonDataState.Success -> {
            val successState = state as PokemonDataState.Success
            PokemonDataScreen(pokemon = successState.pokemon,
                onBackClick = onBackClick)
        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PokemonDataScreen(pokemon: PokemonData, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pokemon?.name ?: "PokemonData") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        pokemon?.let {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Fit,
                    error = painterResource(R.drawable.error_image), // Icono de fallback
                    placeholder = painterResource(R.drawable.placeholder) // Placeholder
                )

                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = "Type: ${pokemon.type}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "Height: ${pokemon.height} m",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "Weight: ${pokemon.weight} kg",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Description:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = pokemon.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Stats:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                pokemon.stats.forEach { (stat, value) ->
                    Text(
                        text = "$stat: $value",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
            }
        }
    }
}