package com.pepinho.nba.ui.equipo

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.nba.model.Equipo
import com.pepinho.nba.model.repositorios.EquipoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class EquipoViewModel(private val repositorio: EquipoRepository, idEquipo: Int = 0) : ViewModel() {


    private val _equipo = MutableStateFlow<Equipo?>(null)
    val equipo: StateFlow<Equipo?> get() = _equipo

    private val _loadingState = MutableStateFlow<Boolean>(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

//    init {
//        if (idEquipo != 0)
//            getEquipoById(idEquipo)
//    }

    fun getRandomEquipo() {
        viewModelScope.launch {
            _loadingState.value = true
            val randomEquipo = repositorio.equipos.value.randomOrNull()
            _equipo.value = randomEquipo
            _loadingState.value = false
        }
    }

    fun getEquipoById(id: Int) {
        viewModelScope.launch {
            _loadingState.value = true
            val equipo = repositorio.getEquipoById(id)
            _equipo.value = equipo
            _loadingState.value = false
        }
    }


    /**
     * De momento este método no lo vamos a usar
     */
    private fun imageToBitmap(image: ImageView): ByteArray {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }

}