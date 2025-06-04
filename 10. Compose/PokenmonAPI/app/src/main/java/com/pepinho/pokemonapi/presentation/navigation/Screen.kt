package com.pepinho.pokemonapi.presentation.navigation

sealed class Screen(val rout: String) {
    object Inicio: Screen("inicio_screen")
    object Pokemon: Screen("pokemon_screen")
    object Search: Screen("search_screen")
    object Setting: Screen("setting_screen")
    object Detail: Screen("detail/{pokemonid}")

}