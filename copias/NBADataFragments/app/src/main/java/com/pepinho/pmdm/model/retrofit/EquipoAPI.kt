package com.pepinho.pmdm.model.retrofit

import com.pepinho.pmdm.model.Equipo
import com.pepinho.pmdm.model.gson.Equipos
import retrofit2.http.GET

interface EquipoAPI {
    @GET("teams")
    suspend fun getEquipos(): Equipos
}