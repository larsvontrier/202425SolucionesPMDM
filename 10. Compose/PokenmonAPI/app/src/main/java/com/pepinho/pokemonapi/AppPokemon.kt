package com.pepinho.pokemonapi

import android.app.Application
import com.pepinho.pokemonapi.data.AppContainer
import com.pepinho.pokemonapi.data.AppContainerImpl


class AppPokemon : Application() {

    lateinit var contenedor: AppContainer

    override fun onCreate() {
        super.onCreate()
        contenedor = AppContainerImpl()
    }

//    val repository: PokemonRepository by lazy {
//        PokemonRepositoryImpl(PokemonApi.getInstance())
//    }
}