package com.pepinho.pmdm.exames.model

import android.app.Application

class CafeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        CafeRepository.initialize(this)
    }
}