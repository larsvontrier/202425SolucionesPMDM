package com.pepinho.pmdm.applistas.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pepinho.pmdm.applistas.model.PokemonResponse
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
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