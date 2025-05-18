package com.pepinho.pmdm.pokemonnav.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pepinho.pmdm.pokemonnav.ui.components.PokemonCard
import com.pepinho.pmdm.pokemonnav.viewmodel.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: PokemonViewModel) {
    var query by remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Búsca un Pokémon") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.searchPokemon(query) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Búsqueda")
            }

            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                items(searchResults) { pokemon ->
                    PokemonCard(
                        pokemon = pokemon,
                        onClick = { /* Al hacer clic sobre le pokemon */ }
                    )
                }
            }
        }
    }
}