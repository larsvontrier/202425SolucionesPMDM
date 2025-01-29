package com.pepinho.pmdm.model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteRetrofit {
    private const val BASE_URL = "https://api.balldontlie.io/v1/"

    val apiEquipo: EquipoAPI by lazy {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        retrofit.create(EquipoAPI::class.java)
    }
}