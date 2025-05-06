package com.pepinho.fotogrid.data.dependencies


import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pepinho.fotogrid.data.ApiFotosRepository
import com.pepinho.fotogrid.data.FotosRepository
import com.pepinho.fotogrid.retrofit.PicsumApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


/**
 * Implementación del contenedor de inyección de dependencias a nivel de la aplicación.
 * Las variables se inicializan de forma perezosa y la misma instancia se comparte en toda la aplicación.
 */
class AppInyectorContainerImpl : AppInyectorContainer {
    private val baseUrl = "https://picsum.photos/"

    /**
     * Usamos el constructor de Retrofit para crear un objeto retrofit usando un
     * convertidor de kotlinx.serialization (en otros ejemplos he usado Gson o Moshi)
     */
    private val retroJson = Json { ignoreUnknownKeys = true } // Ignora las claves desconocidas en el JSON (para que no falle la serialización si hay cambios en el JSON)
    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .addConverterFactory(retroJson.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Objeto de servicio Retrofit para crear llamadas a la API
     */
    private val retrofitService: PicsumApi by lazy {
        retrofit.create(PicsumApi::class.java)
    }

    /**
     * ApiFotosRepository es la interfaz que define el contrato para el repositorio de fotos.
     * Implementación de la interfaz FotosRepository.
     */
    // Instanciación perezosa de ApiFotosRepository. Se crea la instancia cuando se necesita la
    // primera vez y se reutiliza en toda la aplicación.
    override val fotosRepository: FotosRepository by lazy {
        ApiFotosRepository(retrofitService)
    }
}