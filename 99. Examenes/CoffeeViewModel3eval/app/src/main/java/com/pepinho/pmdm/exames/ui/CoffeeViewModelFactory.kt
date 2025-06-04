package com.pepinho.pmdm.exames.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pepinho.pmdm.exames.CoffeeViewModel
import com.pepinho.pmdm.exames.repository.CafeRepository

class CoffeeViewModelFactory(private val repository: CafeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoffeeViewModel::class.java)) {
            return CoffeeViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }

}