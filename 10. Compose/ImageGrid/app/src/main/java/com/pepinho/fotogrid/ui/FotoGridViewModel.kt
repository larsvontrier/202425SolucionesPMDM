package com.pepinho.fotogrid.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pepinho.fotogrid.FotoGridApplication
import com.pepinho.fotogrid.data.FotosRepository
import com.pepinho.fotogrid.model.PicsumPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * ViewModel para la pantalla de inicio.
 * No difiere de un ViewModel normal, pero se utiliza para mostrar el estado de la UI.
 * Recuerda que el ViewModel no tiene acceso a la UI, por lo que no puede mostrar el estado directamente.
 * COmo recoge el repositorio, he creado un objeto Factory para que pueda ser inyectado.
 */

class FotoGridViewModel(private val fotosRepository: FotosRepository) : ViewModel() {
    /**
     * El estado mutable que almacena el estado de la última solicitud
     * */
    var picsumUiState: PicsumUiState by mutableStateOf(PicsumUiState.Loading)
        private set

    /**
     * Al cargar, invoca getFotos() para que pueda mostrar el estado inmediatamente.
     */
    init {
        getFotos()
    }

    /**
     * Obtiene información de las fotos de Picsum de la API de Retrofit y actualiza el estado
     * [PicsumPhoto] [List] [MutableList].
     */
    fun getFotos() {
        viewModelScope.launch {
            picsumUiState = PicsumUiState.Loading
            picsumUiState = try {
                PicsumUiState.Success(fotosRepository.getFotos())
            } catch (e: IOException) {
                PicsumUiState.Error
            } catch (e: HttpException) {
                PicsumUiState.Error
            }
        }
    }

    /**
     * Factory para [FotoGridViewModel] que toma el repositorio [FotosRepository] como dependencia
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FotoGridApplication)
                val picsumRepository = application.contenedor.fotosRepository
                FotoGridViewModel( fotosRepository = picsumRepository)
            }
        }
    }
}
