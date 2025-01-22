package com.pepinho.pmdm.exames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.pmdm.exames.model.CafeConCategoria
import com.pepinho.pmdm.exames.model.CafeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoffeeViewModel(private val repositorio: CafeRepository): ViewModel() {
    private val _cafeConCategoria = MutableStateFlow(CafeConCategoria())
    val cafeConCategoria = _cafeConCategoria.asStateFlow()

    init {
        getCoffeConCategoria()
    }


    fun getCoffeConCategoria(){
        viewModelScope.launch {
            _cafeConCategoria.value = repositorio.getCafeConCategoriaRandom()
        }
    }

}