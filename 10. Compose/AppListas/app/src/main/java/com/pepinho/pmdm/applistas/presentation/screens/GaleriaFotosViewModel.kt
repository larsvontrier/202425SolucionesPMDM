package com.pepinho.pmdm.applistas.presentation.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.pmdm.applistas.api.ElementoGaleria
import com.pepinho.pmdm.applistas.api.FotosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "GaleriaFotosViewModel"

class GaleriaFotosViewModel : ViewModel() {
    private val photoRepository = FotosRepository()

    private val _galleryItems: MutableStateFlow<List<ElementoGaleria>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<ElementoGaleria>>
        get() = _galleryItems.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotos()
                Log.d(TAG, "Elementos recibidos: $items")
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Error al obtener los elementos de la galería", ex)
            }
        }
    }
}