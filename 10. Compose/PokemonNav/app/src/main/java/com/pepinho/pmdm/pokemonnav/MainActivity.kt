package com.pepinho.pmdm.pokemonnav


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.pepinho.pmdm.pokemonnav.navigation.PokedexNavHost
import com.pepinho.pmdm.pokemonnav.ui.components.BottomNavBar
import com.pepinho.pmdm.pokemonnav.ui.theme.PokemonNavTheme
import com.pepinho.pmdm.pokemonnav.viewmodel.PokemonViewModel
import com.pepinho.pmdm.pokemonnav.viewmodel.PokemonViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Iniciamos el contenedor de la aplicación
            val application = application as PokeApplication
            val viewModel: PokemonViewModel = viewModel(
                factory = PokemonViewModelFactory(application.appContainer.pokemonRepository) // Usamos el factory para crear el ViewModel
            )
            PokemonNavTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavBar(navController = navController) }
                ) { innerPadding ->
                    PokedexNavHost(
                        navController = navController,
                        viewModel = viewModel, // Le pasamos el ViewModel a la navegación
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}