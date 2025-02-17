package com.pepinho.pmdm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.pmdm.model.retrofit.ClienteRetrofit
import com.pepinho.pmdm.model.Equipo
import kotlinx.coroutines.launch

class EquiposViewModel: ViewModel() {
    private val _equipos = MutableLiveData<List<Equipo>>()
    val equipos: LiveData<List<Equipo>> get() = _equipos

    fun getEquipost() {
        viewModelScope.launch {
            try {
                _equipos.value = ClienteRetrofit.apiEquipo.getEquipos().equipos
            } catch (e: Exception) {
                Log.e("EquiposViewModel", "${e.message}")
            }
        }
    }
}