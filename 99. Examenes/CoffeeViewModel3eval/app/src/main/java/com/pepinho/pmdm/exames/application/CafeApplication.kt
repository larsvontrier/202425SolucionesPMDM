package com.pepinho.pmdm.exames.application

import android.app.Application
import com.pepinho.pmdm.exames.repository.CafeRepository

class CafeApplication: Application() {

//    val repository: CafeRepository by lazy {
//        CafeRepository.getInstance(this)
//    }

    override fun onCreate() {
        super.onCreate()
        CafeRepository.getInstance(this)
    }
}