package com.pepinho.fotogrid.ui

import com.pepinho.fotogrid.model.PicsumPhoto

/**
 * Estado de la UI para InicioScreen.
 */
sealed interface PicsumUiState {
    data class Success(val photos: List<PicsumPhoto>) : PicsumUiState
    object Error : PicsumUiState
    object Loading : PicsumUiState
}