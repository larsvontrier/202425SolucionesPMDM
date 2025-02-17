package com.pepinho.nba

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.nba.model.Equipo
import com.pepinho.nba.retrofit.ClienteRetrofit
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