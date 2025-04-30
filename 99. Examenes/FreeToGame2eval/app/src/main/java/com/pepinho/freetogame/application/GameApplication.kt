package com.pepinho.freetogame.application

import android.app.Application
import com.pepinho.freetogame.repositories.JuegoDbRepository
import com.pepinho.freetogame.repositories.JuegoRepository

class GameApplication : Application() {
    val respository: JuegoRepository by lazy {
        JuegoDbRepository.getInstance(this)
    }
}