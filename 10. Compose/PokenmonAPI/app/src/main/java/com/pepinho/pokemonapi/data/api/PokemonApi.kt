package com.pepinho.pokemonapi.data.api

import com.pepinho.pokemonapi.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("api/v2/pokemon")
    suspend fun fetchPokemons(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 200
    ): PokemonResponse

//    companion object {
//        private const val baseUrl = "https://pokeapi.co/"
//
//        fun getInstance(): PokemonApi {
//            val contentType = "application/json".toMediaType()
//            val json = Json { ignoreUnknownKeys = true }
//
//            return Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(json.asConverterFactory(contentType))
//                .build()
//                .create(PokemonApi::class.java)
//        }
//    }
}