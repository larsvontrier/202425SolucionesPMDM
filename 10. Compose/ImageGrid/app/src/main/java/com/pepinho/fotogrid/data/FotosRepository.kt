
package com.pepinho.fotogrid.data

import com.pepinho.fotogrid.model.PicsumPhoto

/**
 * Repositorio genérico que obtiene la lista de fotos de Picsum de PicsumApi.
 * Permite cambiar de fuente de datos sin cambiar el código del ViewModel.
 * Aunque debería declararse una data class genérica para cambiar el tipo de datos y la fuente.
 * Ejemplo:
 * interface FotosRepository<T> {
 *   suspend fun getFotos(): List<T>
 * }
 *
 * Y su implementación:
 *
 * class ApiFotosRepository(
 *    private val servicioPicsum: PicsumApi
 *    ) : FotosRepository<PicsumPhoto> {
 *    suspend fun getFotos(): List<PicsumPhoto> = servicioPicsum.getFotos()
 *    }
 *
 *
 */
interface FotosRepository {
    /**
     * Devuelve una [List] de [PicsumPhoto]. Este método debe ser llamado desde una Corrutina.
     * Debería declararse una data class genérica para cambiar el tipo de datos y la fuente.
     * */
    suspend fun getFotos(): List<PicsumPhoto>
}