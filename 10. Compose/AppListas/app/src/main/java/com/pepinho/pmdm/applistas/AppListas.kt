package com.pepinho.pmdm.applistas

import android.app.Application
import com.pepinho.pmdm.applistas.data.AppContainer
import com.pepinho.pmdm.applistas.data.AppContainerImpl


class AppListas : Application() {

    lateinit var contenedor: AppContainer

    override fun onCreate() {
        super.onCreate()
        contenedor = AppContainerImpl()
    }

//    val repository: PokemonRepository by lazy {
//        PokemonRepositoryImpl(PokemonApi.getInstance())
//    }
}