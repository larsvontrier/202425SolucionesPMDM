package com.pepinho.pmdm.pokemonnav

import android.app.Application
import com.pepinho.pmdm.pokemonnav.di.AppContainer
import com.pepinho.pmdm.pokemonnav.di.AppContainerImpl

class PokeApplication : Application () {
    // Inicializa el contenedor de dependencias
    val appContainer: AppContainer by lazy {
        AppContainerImpl()
    }
}