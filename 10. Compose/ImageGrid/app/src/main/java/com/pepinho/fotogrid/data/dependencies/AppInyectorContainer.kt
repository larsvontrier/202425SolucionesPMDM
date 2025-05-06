package com.pepinho.fotogrid.data.dependencies
// Inyección manual de dependencias: https://developer.android.com/training/dependency-injection/manual?hl=es-419

// - Retrofit
// - ApiFotosRepository
// - ApiFotosService

import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pepinho.fotogrid.data.ApiFotosRepository
import com.pepinho.fotogrid.data.FotosRepository
import com.pepinho.fotogrid.retrofit.PicsumApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

/**
 * Contenedor de inyección de dependencias a nivel de la aplicación
 */
interface AppInyectorContainer {
    val fotosRepository: FotosRepository
}

