package com.pepinho.pmdm.applistas.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FotoResponse(
    @Json(name = "photo") val elementoGalerias: List<ElementoGaleria>
)
