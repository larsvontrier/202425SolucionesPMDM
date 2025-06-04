package com.pepinho.pokemonapi

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pepinho.pokemonapi.presentation.navigation.Screen
import com.pepinho.pokemonapi.presentation.screens.BottomBar
import com.pepinho.pokemonapi.presentation.screens.home.HomeScreen
import com.pepinho.pokemonapi.presentation.screens.pokemon.PokemonScreen
import com.pepinho.pokemonapi.presentation.screens.PokemonViewModel
import com.pepinho.pokemonapi.presentation.screens.search.SearchScreen
import com.pepinho.pokemonapi.presentation.screens.SettingScreen
import com.pepinho.pokemonapi.presentation.screens.detail.PokemonDetailScreen
import com.pepinho.pokemonapi.presentation.ui.theme.AppListasTheme

class MainActivity : ComponentActivity() {
//    val repository = PokemonRepositoryImpl(PokemonApi.getInstance())
//    val viewModel = ViewModelProvider(
//        this,
//        PokemonViewModelFactory(repository)
//    )[PokemonViewModel::class.java]

    @RequiresApi(Build.VERSION_CODES.O) // Requiere API 26 o superior para LocalDateTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppListasTheme {
                MainScreen()
            }
        }
    }

}

@Composable
private fun MainScreen() {
    // Creamos el ViewModel. Existe otro modo de hacerlo que es por medio de la inyección de dependencias o DI.
    val viewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.Factory)
    val navController = rememberNavController() // Obtenemos el controlador de navegación

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        BottomBar(
            onClick = { /* Acción al hacer clic en la barra de navegación */ },
            selectedScreen = Screen.Pokemon.rout, // Cambia esto según la pantalla seleccionada
            navController = navController,
            viewModel = viewModel,
        )
    }) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Pokemon.rout,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Inicio.rout) {
                HomeScreen()
            }
            composable(route = Screen.Setting.rout) {
                SettingScreen()
            }
            composable(route = Screen.Pokemon.rout) {
                PokemonScreen(
                    viewModel,
                    Modifier.padding(innerPadding),
                    navController = navController
                )
            }
            composable(route = Screen.Search.rout) {
                SearchScreen()
            }
            composable(route = "detail/{pokemonId}") { backStackEntry ->

                val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull()

                Log.d("MainActivity", "Pokemon ID: $pokemonId")

                if (pokemonId != null) {
                    viewModel.getPokemonById(pokemonId.toInt())
                    PokemonDetailScreen(
                        viewModel = viewModel,
                        onBackClick = { navController.popBackStack() },)
                } else {
                    // Manejo de error: ID de Pokémon no proporcionado
                }
            }
        }

//        PokemonScreen(viewModel, Modifier.padding(innerPadding))
    }
}
