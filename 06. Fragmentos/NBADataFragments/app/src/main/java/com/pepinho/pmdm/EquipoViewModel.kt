package com.pepinho.pmdm

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.icu.text.Transliterator.Position
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.pepinho.pmdm.model.Conferencia
import com.pepinho.pmdm.model.Division
import com.pepinho.pmdm.model.Equipo
import com.pepinho.pmdm.model.enumToList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.ByteArrayOutputStream

class EquipoViewModel: ViewModel(){

    private val _equipo = MutableStateFlow(Equipo(photo = null))
    val equipo : StateFlow<Equipo> = _equipo.asStateFlow()

    fun getConferencias() : List<String> {
        return enumToList<Conferencia>()
    }

    fun getDivisiones() : List<String> {
        return enumToList<Division>()
    }

    fun setAbreviatura(abreviatura: String){
        _equipo.value = _equipo.value.copy(abreviatura = abreviatura)
    }

    fun setNombre(nombre: String){
        _equipo.value = _equipo.value.copy(nombre = nombre)
    }

    fun setNombreCompleto(nombreCompleto: String){
        _equipo.value = _equipo.value.copy(nombreCompleto = nombreCompleto)
    }

    fun setCiudad(ciudad: String){
        _equipo.value = _equipo.value.copy(ciudad = ciudad)
    }

    fun setDivision(position: Int){
        _equipo.value = _equipo.value.copy(division = Division.values()[position])
    }

    fun setConferencia(position: Int){
        _equipo.value = _equipo.value.copy(conferencia = Conferencia.values()[position])
    }

    fun setFavorito(favorito: Boolean = false){
        _equipo.value = _equipo.value.copy(isFavorito = favorito)
    }

    fun setPhoto(image : ImageView){
        _equipo.value = _equipo.value.copy(photo = imageToBitmap(image))
    }

    private fun imageToBitmap(image: ImageView): ByteArray {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)

        return stream.toByteArray()
    }

}