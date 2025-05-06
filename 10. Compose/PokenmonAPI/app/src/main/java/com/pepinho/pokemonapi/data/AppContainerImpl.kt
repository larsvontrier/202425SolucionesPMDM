package com.pepinho.pokemonapi.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pepinho.pokemonapi.data.api.PokemonApi
import com.pepinho.pokemonapi.data.repository.PokemonRepository
import com.pepinho.pokemonapi.data.repository.PokemonRepositoryImpl
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Interfaz que define el contenedor de la aplicación. Se trata de implementación manual de
 * inyección de dependencias.
 * Como sólo precisamos un repositorio, incluiomos únicamente el repositorio de Pokémon.
 * Podríamos incluir más repositorios si fuera necesario u otros servicios, como bases de datos, etc.
 */

class AppContainerImpl : AppContainer {
    private val baseUrl = "https://pokeapi.co/"

    private val retroJson = Json { ignoreUnknownKeys = true } // Configuración de Json para ignorar claves desconocidas

    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .addConverterFactory(retroJson.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitApi: PokemonApi by lazy {
        retrofit.create(PokemonApi::class.java)
    }

    override val pokemonRepository: PokemonRepository by lazy {
        PokemonRepositoryImpl(retrofitApi)
    }
}