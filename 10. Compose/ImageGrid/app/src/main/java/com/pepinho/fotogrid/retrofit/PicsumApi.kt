
package com.pepinho.fotogrid.network

import com.pepinho.fotogrid.model.PicsumPhoto
import retrofit2.http.GET

/**
 * Interface pública que expone el método [getFotos]
 */
interface PicsumApi {
    /**
     * Devuelve una [List] de [PicsumPhoto]. Este método debe ser llamado desde una Corrutina.
     * La anotación @GET indica que el endpoint "v2/list?page=2&limit=100" será solicitado con el método HTTP GET
     */
//    @GET("v2/list")
    @GET("v2/list?page=3&limit=100")
    suspend fun getFotos(): List<PicsumPhoto>
}
