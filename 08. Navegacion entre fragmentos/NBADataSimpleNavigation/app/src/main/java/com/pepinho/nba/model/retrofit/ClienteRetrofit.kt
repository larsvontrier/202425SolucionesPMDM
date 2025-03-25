package com.pepinho.nba.retrofit

import com.google.gson.GsonBuilder
import com.pepinho.nba.gson.Equipos
import com.pepinho.nba.gson.EquiposTypeAdapter
import com.pepinho.nba.model.Equipo
import com.pepinho.pmdm.nba.gson.EquipoTypeAdapter
import com.pepinho.pmdm.nba.retrofit.EquipoAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteRetrofit {
    private const val BASE_URL = "https://api.balldontlie.io/v1/"
    private const val API_KEY = "7ff6967d-dae3-4b4c-a716-b0c773b511e1"

    val apiEquipo: EquipoAPI by lazy {
        val gson = GsonBuilder().setPrettyPrinting().
            registerTypeAdapter(Equipos::class.java, EquiposTypeAdapter())
                .registerTypeAdapter(Equipo::class.java, EquipoTypeAdapter()).create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient(API_KEY).build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        retrofit.create(EquipoAPI::class.java)
    }

    private fun okHttpClient(apiKey: String) = OkHttpClient().newBuilder()
        .addInterceptor(
            object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request = chain.request()
                        .newBuilder()
                        .header("accept", "application/json")
                        .header("Authorization", "${apiKey}")
                        .build()
                    return chain.proceed(request)
                }
            }
        )
}