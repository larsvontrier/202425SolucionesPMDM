package com.pepinho.nba.application

import android.app.Application
import com.pepinho.nba.model.repositorios.EquipoJsonRepository
import com.pepinho.nba.model.repositorios.EquipoRepository

class NBADataApplication : Application() {
    val respository: EquipoRepository by lazy {
        EquipoJsonRepository.getInstance(this)
    }

}