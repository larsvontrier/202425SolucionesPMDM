package com.pepinho.pmdm.pokemonnav.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pepinho.pmdm.pokemonnav.screens.detail.PokemonDetailScreen
import com.pepinho.pmdm.pokemonnav.screens.home.PokemonListScreen
import com.pepinho.pmdm.pokemonnav.screens.search.SearchScreen
import com.pepinho.pmdm.pokemonnav.screens.settings.SettingsScreen
import com.pepinho.pmdm.pokemonnav.viewmodel.PokemonViewModel

@Composable
fun PokedexNavHost(
    navController: NavHostController,
    viewModel: PokemonViewModel,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.route
    ) {
        composable(NavItem.Home.route) {
            PokemonListScreen(
                viewModel = viewModel,
                onPokemonClick = { pokemonId ->
                    navController.navigate("detail/$pokemonId")
                }
            )
        }

        composable(NavItem.Search.route) {
            SearchScreen(viewModel = viewModel)
        }

        composable(NavItem.Settings.route) {
            SettingsScreen()
        }

        composable("detail/{pokemonId}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull()
            pokemonId?.let {
                viewModel.selectPokemon(it)
                PokemonDetailScreen(
                    pokemon = viewModel.selectedPokemon.value,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}