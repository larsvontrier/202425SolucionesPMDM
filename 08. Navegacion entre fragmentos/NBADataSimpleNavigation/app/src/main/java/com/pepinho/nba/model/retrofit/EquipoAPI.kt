package com.pepinho.pmdm.nba.retrofit

import com.pepinho.nba.gson.Equipos
import retrofit2.http.GET

interface EquipoAPI {
    @GET("teams")
    suspend fun getEquipos(): Equipos
}