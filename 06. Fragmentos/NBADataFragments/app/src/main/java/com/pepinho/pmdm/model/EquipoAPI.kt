package com.pepinho.pmdm.model

import retrofit2.http.GET

interface EquipoAPI {
    @GET("teams")
    suspend fun getEquipos(): List<Equipo>
}