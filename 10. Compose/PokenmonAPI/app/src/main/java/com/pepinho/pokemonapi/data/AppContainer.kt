package com.pepinho.pokemonapi.data

import com.pepinho.pokemonapi.data.repository.PokemonRepository

/**
 * Interfaz que define el contenedor de la aplicación.
 * La implementación de esta interfaz proporciona acceso a los repositorios de datos.
 * Se usa para realizar una inyección de dependencias en la aplicación de manera manual.
 * Otro modo de hacerlo es por medio de bibliotecas como Dagger o Hilt.
 * Existe una biblioteca más moderna y sencilla de usar que se llama Koin.
 */
interface AppContainer {
    val pokemonRepository: PokemonRepository
}