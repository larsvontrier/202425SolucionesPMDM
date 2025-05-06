package com.pepinho.pmdm.applistas.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pepinho.pmdm.applistas.data.api.PokemonApi
import com.pepinho.pmdm.applistas.data.repository.PokemonRepository
import com.pepinho.pmdm.applistas.data.repository.PokemonRepositoryImpl
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class AppContainerImpl : AppContainer {
    private val baseUrl = "https://pokeapi.co/"

    private val retroJson = Json { ignoreUnknownKeys = true }

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