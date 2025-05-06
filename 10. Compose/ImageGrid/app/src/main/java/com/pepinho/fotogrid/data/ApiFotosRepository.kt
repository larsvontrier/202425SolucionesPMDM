package com.pepinho.fotogrid.data

import com.pepinho.fotogrid.model.PicsumPhoto
import com.pepinho.fotogrid.retrofit.PicsumApi

/**
 * Implementación de la interfaz FotosRepository
 * que obtiene la lista de fotos de Picsum de PicsumApi.
 */
class ApiFotosRepository(
    private val picsumApi: PicsumApi
) : FotosRepository {
    /**
     * Obtiene una lista de fotos de Picsum de PicsumApi.
     * */
    override suspend fun getFotos(): List<PicsumPhoto> = picsumApi.getFotos()
}