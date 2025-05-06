
package com.pepinho.fotogrid.data

import com.pepinho.fotogrid.model.PicsumPhoto
import com.pepinho.fotogrid.network.PicsumApiService

/**
 * Repositorio que obtiene la lista de fotos de Picsum de PicsumApi.
 */
interface PicsumRepository {
    /**
     * Devuelve una [List] de [PicsumPhoto]. Este método debe ser llamado desde una Corrutina.
     * */
    suspend fun getFotos(): List<PicsumPhoto>
}

/**
 * Implementación de la interfaz PicsumRepository
 * que obtiene la lista de fotos de Picsum de PicsumApi.
 */
class ApiPicsumRepository(
    private val servicioPicsum: PicsumApiService
) : PicsumRepository {
    /**
     * Obtiene una lista de fotos de Picsum de PicsumApi.
     * */
    override suspend fun getFotos(): List<PicsumPhoto> = servicioPicsum.getFotos()
}
