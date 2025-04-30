package com.pepinho.pmdm.contadorviewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContadorViewModel: ViewModel() {
    private var _valor = MutableStateFlow(0)
    val valor : StateFlow<Int> = _valor.asStateFlow()

    fun incremantar(){
        _valor.value++
    }

    fun decrementar(){
        if(_valor.value>0)
            _valor.value--
    }

}