package com.pepinho.nba.application

import android.app.Application
import com.pepinho.nba.model.repositorios.EquipoJsonRepository

class NBADataApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        EquipoJsonRepository.initialize(this)
    }
}