package com.pepinho.fotogrid.data

import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pepinho.fotogrid.network.PicsumApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/**
 * Contenedor de inyección de dependencias a nivel de la aplicación
 */
interface AppContenedorPicsumRepo {
    val fotosRepository: FotosRepository
}

/**
 * Implementación del contenedor de inyección de dependencias a nivel de la aplicación.
 * Las variables se inicializan de forma perezosa y la misma instancia se comparte en toda la aplicación.
 */
class AppContenedorPicsumRepoImpl : AppContenedorPicsumRepo {
    private val baseUrl = "https://picsum.photos/"

    /**
     * Usamos el constructor de Retrofit para crear un objeto retrofit usando un
     * convertidor de kotlinx.serialization
     */
    private val retroJson = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .addConverterFactory(retroJson.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Objeto de servicio Retrofit para crear llamadas a la API
     */
    private val retrofitService: PicsumApiService by lazy {
        retrofit.create(PicsumApiService::class.java)
    }

    /**
     * ApiFotosRepository es la interfaz que define el contrato para el repositorio de fotos.
     * Implementación de la interfaz FotosRepository.
     */
    override val fotosRepository: FotosRepository by lazy {
        ApiFotosRepository(retrofitService)
    }
}
